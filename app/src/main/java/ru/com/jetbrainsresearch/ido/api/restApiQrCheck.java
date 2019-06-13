package ru.com.jetbrainsresearch.ido.api;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class restApiQrCheck {

    Lambd

    public void restApiQrCodeCheck (String string, lambda) {
        ArrayList<String> barCodes = new ArrayList<String>();
        barCodes.add(String.valueOf(string).replaceAll("Format: QR_CODE Contents: " + "RAW bytes: \\(64 bytes\\) EC level: M Barcode image: null", ""));
        for (int codeIndex = 0; codeIndex < barCodes.size(); ++codeIndex) {
            String parameters = barCodes.get(codeIndex);
            int newChecks = 0;
            try {
                String apiUrl = "https://rest-fns-check.herokuapp.com/check/" + parameters;
                URL url = new URL(
                        apiUrl
                );
//                Log.i(TAG,"API URL: " + apiUrl);

                URLConnection urlc = url.openConnection();
                urlc.setAllowUserInteraction(false);
                urlc.setUseCaches(false);
                urlc.setConnectTimeout(30000);
                urlc.setReadTimeout(30000);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(urlc.getInputStream())
                );
                String jsonResult = "";
                String line = null;
                while ((line = br.readLine()) != null) {
                    jsonResult += line;
                }
                br.close();

                try {
                    JSONObject result = new JSONObject(jsonResult);
                    JSONArray items = (JSONArray) result.get("items");
                    String time = (String) result.get("check_time");
                    String check_description = (String) result.get("check_description");

//                    Log.i(TAG, "Check: " + check_description);
                    for (int i = 0; i < items.length(); ++i) {
                        JSONArray itemRaw = (JSONArray) items.get(i);
                        String name = (String) itemRaw.get(0);
                        Float price = ((Number) itemRaw.get(1)).floatValue();
                        Float count = ((Number) itemRaw.get(2)).floatValue();
                        String category = (String) itemRaw.get(3);
//                        Log.i(TAG, "Item: " + name + "\t" + category + "\t" + price.toString() + "\t" + count.toString());
                    }
                    String rawResponse = (String) result.get("raw_response");
                    lambda(rawResponse)
//                    Log.i(TAG,"Raw response: " + rawResponse);
                } catch (org.json.JSONException e) {
                }
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
        }

    }
}

