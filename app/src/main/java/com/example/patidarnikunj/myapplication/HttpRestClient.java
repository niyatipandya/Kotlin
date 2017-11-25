package com.example.patidarnikunj.myapplication;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;

public class HttpRestClient {

    // public static String BASE_URL = "http://access24.wedowebapps.in/wp-json/api/";
    public static String BASE_URL = "http://pipcars.wedowebapps.in/api/auth/";

    private static AsyncHttpClient client = new AsyncHttpClient();


    public static void get(String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {

        client.get(getAbsoluteUrl(url), params, responseHandler);

        //Log.v("URL", client.getUrlWithQueryString(getAbsoluteUrl(url), params));
        Log.v("URL", client.getUrlWithQueryString(true, getAbsoluteUrl(url), params));
    }

    public static void post(String url, RequestParams params,
                            AsyncHttpResponseHandler responseHandler, HashMap<String, String> headers) {
        for (String key : headers.keySet()) {
            client.addHeader(key, headers.get(key));
        }
        client.post(getAbsoluteUrl(url), params, responseHandler);
        Log.v("URL", client.getUrlWithQueryString(true, getAbsoluteUrl(url), params));
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        //client.setTimeout(40*1000);
        return BASE_URL + relativeUrl;

    }

    public static void cancelClient(Context context) {
        client.cancelRequests(context, true);
    }

}
