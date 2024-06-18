package ca.jrvs.apps.http;

import ca.jrvs.apps.models.Quote;

import java.io.IOException;
import java.util.Optional;

public interface HttpHelper {

    String fetchQuoteData (String url, String apiKey, String apiHost) throws IOException, InterruptedException;
}
