package com.bitcoinpriceindex.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Optional;
import org.json.*;


public class RequestUtil {

    private static HttpURLConnection urlConnection;
    private static BufferedReader bufferedReader;

    /**
     * Performs simply Http GET method
     *
     * @param urlStr that sent a request
     * @return JSON response
     * @throws IOException
     */
    public static Optional<JSONObject> getRequest(String urlStr) throws IOException {
        Optional<JSONObject> jsonObject = Optional.empty();
        try {
            URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type","application/json");

            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                content.append(inputLine);
            }
             jsonObject = Optional.of(new JSONObject(content.toString()));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            bufferedReader.close();
        }
        return jsonObject;
    }
}
