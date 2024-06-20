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
        QuoteDao quoteDao = new QuoteDao();
        QuoteHttpHelper httpHelper = new QuoteHttpHelper();

        // Initialize the DAO classes

        QuoteService quoteService = new QuoteService(quoteDao, httpHelper);



        // Example: Fetch quote data for a given ticker and save it to the database
        String ticker = "IBM";
        try {
            Optional<Quote> quoteOpt = quoteService.fetchQuoteDataFromAPI(ticker);
            if (quoteOpt.isPresent()) {
                Quote quote = quoteOpt.get();
                System.out.println("Fetched Quote: " + quote);
                logger.info("Fetched Quote: {}", quote);

                // save the fetched quote to the database
                quoteDao.save(quote);
                System.out.println("Saved quote to the database: " + quote);
                logger.info("saved quote to the database: {}", quote);
            } else {
                System.out.println("Quote not found for ticker: " + ticker);
                logger.warn("Quote not found for ticker: {}", ticker);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            logger.error("An error occurred", e);
        }



        // Example: Buy shares and update position
//        String tickerToBuy = ticker;
//        int numberOfSharesToBuy = 10;
//        double pricePerShare = 1500.0;
//        Position position = positionService.buy(tickerToBuy, numberOfSharesToBuy, pricePerShare);
//        System.out.println("Updated Position: " + position);
//        logger.info("Updated Position: {}", position);

        // Example: Sell shares for a given ticker
//        String tickerToSell = "GOOGL";
//        positionService.sell(tickerToSell);
//        System.out.println("Sold all shares for ticker: " + tickerToSell);
//        logger.info("Sold all shares for ticker: {}", tickerToSell);
    }
    }

