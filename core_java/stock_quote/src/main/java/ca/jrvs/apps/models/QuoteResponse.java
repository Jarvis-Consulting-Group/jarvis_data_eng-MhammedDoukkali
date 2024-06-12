package ca.jrvs.apps.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteResponse {
    @JsonProperty("Global Quote")
    private GlobalQuote globalQuote;

    public GlobalQuote getGlobalQuote() {
        return globalQuote;
    }

    public void setGlobalQuote(GlobalQuote globalQuote) {
        this.globalQuote = globalQuote;
    }

    @Override
    public String toString() {
        return "QuoteResponse{" +
                "globalQuote=" + globalQuote +
                '}';
    }
}
