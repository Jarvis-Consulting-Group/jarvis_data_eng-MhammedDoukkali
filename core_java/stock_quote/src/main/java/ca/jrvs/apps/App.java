package ca.jrvs.apps;


import ca.jrvs.apps.dao.PositionDao;
import ca.jrvs.apps.dao.QuoteDao;
import ca.jrvs.apps.http.HttpClientHelper;
import ca.jrvs.apps.http.OkHttpHelper;
import ca.jrvs.apps.http.QuoteFetcher;
import ca.jrvs.apps.http.QuoteHttpHelper;
import ca.jrvs.apps.models.Position;
import ca.jrvs.apps.models.Quote;
import ca.jrvs.apps.models.QuoteResponse;
import ca.jrvs.apps.service.PositionService;
import ca.jrvs.apps.service.QuoteService;
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
import java.util.Optional;


public class App 
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {


        // Initialize the DAO classes
        QuoteDao quoteDao = new QuoteDao();
        PositionDao positionDao = new PositionDao();

        // Initialize the HTTP Helper
        QuoteHttpHelper quoteHttpHelper = new QuoteHttpHelper();

        // Initialize the Service classes
        QuoteService quoteService = new QuoteService(quoteDao, quoteHttpHelper);
        PositionService positionService = new PositionService(positionDao);


        // Example: Fetch quote data for a given ticker
        String ticker = "IBM";
        Optional<Quote> quoteOpt = quoteService.fetchQuoteDataFromAPI(ticker);
        quoteOpt.ifPresent(quote -> {
            System.out.println("Fetched Quote: " + quote);
            logger.info("Fetched Quote: {}", quote);
        });

        // Example: Buy shares and update position
        String tickerToBuy = ticker;
        int numberOfSharesToBuy = 10;
        double pricePerShare = 1500.0;
        Position position = positionService.buy(tickerToBuy, numberOfSharesToBuy, pricePerShare);
        System.out.println("Updated Position: " + position);
        logger.info("Updated Position: {}", position);

        // Example: Sell shares for a given ticker
        String tickerToSell = "GOOGL";
        positionService.sell(tickerToSell);
        System.out.println("Sold all shares for ticker: " + tickerToSell);
        logger.info("Sold all shares for ticker: {}", tickerToSell);
    }
    }

