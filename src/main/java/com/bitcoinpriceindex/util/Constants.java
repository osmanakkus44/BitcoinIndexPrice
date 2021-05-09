package com.bitcoinpriceindex.util;

public final class Constants {
    public static final String HISTORICAL_BPI_URL = "https://api.coindesk.com/v1/bpi/historical/close.json?" +
            "start=2021-04-07&end=2021-05-07&currency=";

    public static final String CURRENT_PRICE_BPI_URL = "https://api.coindesk.com/v1/bpi/currentprice/";

    public static final String[] CURRENCIES = {"USD", "EUR", "GBP", "TRY", "CHF"};
}
