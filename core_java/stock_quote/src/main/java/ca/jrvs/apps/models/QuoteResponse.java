package ca.jrvs.apps.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteResponse {
    @JsonProperty("Global Quote")
    private Quote quote;

    public Quote getGlobalQuote() {
        return quote;
    }

    public void setGlobalQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "QuoteResponse{" +
                "globalQuote=" + quote +
                '}';
    }
}
