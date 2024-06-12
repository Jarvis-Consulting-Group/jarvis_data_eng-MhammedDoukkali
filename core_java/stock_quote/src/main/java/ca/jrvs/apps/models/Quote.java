package ca.jrvs.apps.models;

//import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {
//    @JsonProperty("01. symbol")
    private String symbol;
//    @JsonProperty("05. price")
    private String price;

    public Quote() {}

    public Quote(String symbol, String price) {
        this.symbol = symbol;
        this.price = price;
    }

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
        return "Quote{" +
                "symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
