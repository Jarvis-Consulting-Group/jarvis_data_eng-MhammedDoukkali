package ca.jrvs.apps.service;

import ca.jrvs.apps.dao.QuoteDao;
import ca.jrvs.apps.http.QuoteHttpHelper;
import ca.jrvs.apps.models.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;
//import java.util.Optional;

public class QuoteService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private final QuoteDao quoteDao;
    private final QuoteHttpHelper httpHelper;

    public QuoteService(QuoteDao quoteDao, QuoteHttpHelper httpHelper) {
        this.quoteDao = quoteDao;
        this.httpHelper = httpHelper;
    }



    /**
 * Fetches latest quote data from endpoint
 *
 * @param ticker Ticker symbol for the stock
 * @return Latest quote information or empty optional if ticker symbol not found
 */

    public Optional<Quote> fetchQuoteDataFromAPI(String ticker)  {
        try {
            // Fetch quote data from API using QuoteHttpHelper
            Optional<Quote> quote = httpHelper.fetchQuote(ticker);

            // If quote data is present, save it to the database
            if (quote.isPresent()) {
                quoteDao.save(quote.get());
            }
            return quote;
        } catch (Exception e) {
            logger.error("Error fetching quote data for ticker : " + ticker, e);
            return Optional.empty();
        }
    }



}

