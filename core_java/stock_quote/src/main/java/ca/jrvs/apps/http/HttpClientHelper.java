package ca.jrvs.apps.http;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientHelper implements HttpHelper {

    @Override
    public String fetchQuoteData(String url, String apiKey, String apiHost) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-RapidAPI-key", apiKey)
                .header("X-RapidAPI-host", apiHost)
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Unexpected response code: " + response.statusCode());
        }

        return response.body();
    }
}
