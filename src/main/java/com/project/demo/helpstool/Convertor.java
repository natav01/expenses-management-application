package com.project.demo.helpstool;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.project.demo.exception.ApiRequestException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Component
public class Convertor {
    public Map<String, Double> getExchangeValues() {
        try {
            URL url = new URL("https://api.exchangerate.host/latest");
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root =
                    jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonObj = root.getAsJsonObject();
            return new Gson().fromJson(jsonObj.get("rates").toString(),
                    LinkedTreeMap.class);
        } catch (IOException ex) {
            throw new ApiRequestException("Couldn't get response from " +
                    "https://exchangerate.host");
        }
    }
}
