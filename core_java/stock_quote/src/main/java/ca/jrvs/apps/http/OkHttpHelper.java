package ca.jrvs.apps.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpHelper implements HttpHelper {

    private final OkHttpClient client;

    public OkHttpHelper() {
        this.client = new OkHttpClient();
    }

    @Override
    public String fetchQuoteData (String url, String apiKey, String apiHost) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-RapidAPI-key", apiKey)
                .addHeader("X-RapidAPI-host", apiHost)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " +response.code());
            }
            return response.body().string();
        }
    }
}
