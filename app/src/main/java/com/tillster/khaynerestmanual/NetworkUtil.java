package com.tillster.khaynerestmanual;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil {
    static String TAG = "URLWECREATED";
    private static final String WEATHERBASE_URL = "https://api.kanye.rest/";


// build URL

    public static URL buildURL() {

        Uri buildUri = Uri.parse(WEATHERBASE_URL);

        URL url = null;

        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "Khayne Lives here: " + url);

        return url;

    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {


        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("//A");
            boolean hasinput = scanner.hasNext();

            if (hasinput) {
                return scanner.next();
            } else {
                return null;
                // Toast.makeText(, "No JSON FOUND", Toast.LENGTH_SHORT).show();
            }

        } finally {
            urlConnection.disconnect();

        }
    }
}
