package ca.jrvs.apps.http;

import ca.jrvs.apps.models.Quote;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

public class QuoteHttpHelper {
    private static final String API_KEY = "01c7b11687mshacb1e381461609ep1e8bc5jsn4c627dd7f5c0";
    private static final String API_HOST = "alpha-vantage.p.rapidapi.com";

    private static final String BASE_URL = "https://alpha-vantage.p.rapidapi.com/query";

    public Optional<Quote> fetchQuote(String ticker) {
        try {
            String urlString = String.format("%s?function=GLOBAL_QUOTE&symbol=%s", BASE_URL, ticker);
            URL url = new URL(urlString);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("X-RapidAPI-Key", API_KEY);
            c.setRequestProperty("X-RapidAPI-Host", API_HOST);

            // Read response
//            Scanner scanner = new Scanner(url.openStream());
//            StringBuilder jsonResponse = new StringBuilder();
//            while (scanner.hasNext()) {
//                jsonResponse.append(scanner.nextLine());
//            }
//            scanner.close();
//
//            // Parse the response into a Quote object
//            return parseQuote(jsonResponse.toString());
            int responseCode = c.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Scanner scanner = new Scanner(c.getInputStream());
                StringBuilder jsonResponse = new StringBuilder();
                while (scanner.hasNext()) {
                    jsonResponse.append(scanner.nextLine());
                }
                scanner.close();

                // Parse and return the quote
                return parseQuote(jsonResponse.toString());
            } else {
                System.out.println("Error: Received HTTP response code " + responseCode);
                return Optional.empty();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Optional<Quote> parseQuote(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            JsonNode quoteNode = rootNode.path("Global Quote");
            if (quoteNode.isMissingNode()) {
                return Optional.empty();
            }

            String symbol = quoteNode.path("01. symbol").asText();
            String price = quoteNode.path("05. price").asText();

            Quote quote = new Quote(symbol, price);
            return Optional.of(quote);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


}
