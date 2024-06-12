package ca.jrvs.apps.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlobalQuote {
    @JsonProperty("01. symbol")
    private String symbol;
    @JsonProperty("05. price")
    private String price;

    public String getSymbol() {
        return symbol;
    }
//
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GlobalQuote{" +
                "symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
