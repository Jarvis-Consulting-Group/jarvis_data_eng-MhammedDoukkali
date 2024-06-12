package ca.jrvs.apps;


import ca.jrvs.apps.http.HttpClientHelper;
import ca.jrvs.apps.http.OkHttpHelper;
import ca.jrvs.apps.http.QuoteFetcher;
import ca.jrvs.apps.models.QuoteResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class App 
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private static final String API_KEY = "01c7b11687mshacb1e381461609ep1e8bc5jsn4c627dd7f5c0";
    private static final String API_HOST = "alpha-vantage.p.rapidapi.com";

    private static final String BASE_URL = "https://alpha-vantage.p.rapidapi.com/query";
    public static void main( String[] args )
    {
        String function = "GLOBAL_QUOTE";
        String symbol = "IBM";
        String url = String.format("%s?function=%s&symbol=%s&datatype=json", BASE_URL, function, symbol);

//        QuoteFetcher quoteFetcher = new QuoteFetcher(new HttpClientHelper());
         QuoteFetcher quoteFetcher = new QuoteFetcher(new OkHttpHelper());

        try {
            String response = quoteFetcher.fetchQuoteData(url, API_KEY, API_HOST);
            logger.info("Response Body: " + response);
            parseAndPrintResponse(response);
        } catch(Exception e) {
            logger.error("Error fetching quote data", e);
    }

    }

    private static void parseAndPrintResponse(String jsonResponse) throws  IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);

        JsonNode globalQuoteNode = rootNode.path("Global Quote");
        if (globalQuoteNode.isMissingNode()) {
            System.out.println("Error: Unable to find global quote data in  the response.");
            return;
        }

        QuoteResponse quoteResponse = objectMapper.treeToValue(globalQuoteNode, QuoteResponse.class);
        System.out.println(quoteResponse);
    }
}
