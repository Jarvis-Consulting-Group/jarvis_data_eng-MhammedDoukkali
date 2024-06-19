package ca.jrvs.apps.models;

public class Position {

    private String ticker; //id
    private int numOfShares;
    private double valuePaid; //total amount paid for shares

    public Position() {}

    public Position(String ticker, int numOfShares, double valuePaid) {
        this.ticker = ticker;
        this.numOfShares = numOfShares;
        this.valuePaid = valuePaid;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getNumOfShares() {
        return numOfShares;
    }

    public void setNumOfShares(int numOfShares) {
        this.numOfShares = numOfShares;
    }

    public double getValuePaid() {
        return valuePaid;
    }

    public void setValuePaid(double valuePaid) {
        this.valuePaid = valuePaid;
    }

    @Override
    public String toString() {
        return "Position{" +
                "ticker='" + ticker + '\'' +
                ", numOfShares='" + numOfShares + '\'' +
                ", valuepaid='" + valuePaid + '\'' +
                '}';
    }
}