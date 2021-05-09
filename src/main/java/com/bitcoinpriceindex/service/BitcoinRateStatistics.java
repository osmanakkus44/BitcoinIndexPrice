package com.bitcoinpriceindex.service;

import org.json.JSONException;
import org.json.JSONObject;
import com.bitcoinpriceindex.util.Constants;
import com.bitcoinpriceindex.util.RequestUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * This class aimed to provide historical and current
 * bitcoin rates for given currency.
 */
public class BitcoinRateStatistics {
    private Double currentBtcRate;
    private List<Double> monthlyBtcRates;
    private String currency;

    public BitcoinRateStatistics() {
        this.currency = "USD";
        fetchData();
    }

    public Double getCurrentBtcRate() {
        return currentBtcRate;
    }

    public Double getLowestBtcRateInLastMonth() {
        return monthlyBtcRates.get(0);
    }

    public Double getHighestBtcRateInLastMonth() {
        return monthlyBtcRates.get(monthlyBtcRates.size()-1);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) throws UnsupportedOperationException{
        if (!Arrays.asList(Constants.CURRENCIES).contains(currency.toUpperCase())) {
            throw new UnsupportedOperationException("Please enter a valid currency: " + Arrays.toString(Constants.CURRENCIES));
        }

        if (!this.currency.equalsIgnoreCase(currency)) {
            this.currency = currency;
            fetchData();
        }
    }

    public void fetchData() {
        try {
            fetchCurrentBtcRate();
            fetchMonthlyBtcRates();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchCurrentBtcRate() throws IOException {
        Optional<JSONObject> jsonObject =
                RequestUtil.getRequest(Constants.CURRENT_PRICE_BPI_URL + currency.toLowerCase() + ".json");
        jsonObject.ifPresent(obj -> {
            try {
                JSONObject bpi = obj.getJSONObject("bpi");
                currentBtcRate = bpi.getJSONObject(currency.toUpperCase()).getDouble("rate_float");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    private void fetchMonthlyBtcRates() throws IOException {
        Optional<JSONObject> jsonObject =
                RequestUtil.getRequest(Constants.HISTORICAL_BPI_URL + currency.toLowerCase());
        jsonObject.ifPresent(obj -> {
            try {
                monthlyBtcRates = new ArrayList<>();
                JSONObject bpi = obj.getJSONObject("bpi");
                for (int i = 0; i < bpi.names().length(); i++) {
                    monthlyBtcRates.add(bpi.getDouble(bpi.names().getString(i)));
                }
                monthlyBtcRates.sort(Double::compareTo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
}
