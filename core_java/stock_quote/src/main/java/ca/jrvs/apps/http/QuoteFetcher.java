package ca.jrvs.apps.http;

import java.io.IOException;

public class QuoteFetcher {

    private final HttpHelper httpHelper;

    public QuoteFetcher (HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    public String fetchQuoteData(String url, String apiKey, String apiHost) throws IOException, InterruptedException {
        return httpHelper.fetchQuoteData(url, apiKey, apiHost);
    }
}
