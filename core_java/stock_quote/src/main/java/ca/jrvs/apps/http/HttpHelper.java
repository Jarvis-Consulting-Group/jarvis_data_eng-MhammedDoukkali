package ca.jrvs.apps.http;

import java.io.IOException;

public interface HttpHelper {

    String fetchQuoteData (String url, String apiKey, String apiHost) throws IOException, InterruptedException;
}
