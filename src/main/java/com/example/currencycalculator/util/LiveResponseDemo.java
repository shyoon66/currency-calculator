package com.example.currencycalculator.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LiveResponseDemo {
    // essential URL structure is built using constants
    private static final String ACCESS_KEY = "f958c0d9edfb1ac4694c1781d1b5ae09";
    private static final String BASE_URL = "http://apilayer.net/api/";
    private static final String ENDPOINT = "live";

    // this object is used for executing requests to the (REST) API
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     *
     * Notes:
     *
     * A JSON response of the form {"key":"value"} is considered a simple Java JSONObject.
     * To get a simple value from the JSONObject, use: .get("key");
     *
     * A JSON response of the form {"key":{"key":"value"}} is considered a complex Java JSONObject.
     * To get a complex value like another JSONObject, use: .getJSONObject("key")
     *
     * Values can also be JSONArray Objects. JSONArray objects are simple, consisting of multiple JSONObject Objects.
     *
     *
     */
    public static Double sendLiveRequest(String country) {
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY);
        double result = 0.0d;

        try(CloseableHttpResponse response =  httpClient.execute(get)) {
            HttpEntity entity = response.getEntity();

            // the following line converts the JSON Response to an equivalent Java Object
            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
            result = exchangeRates.getJSONObject("quotes").getDouble(country);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }
}
