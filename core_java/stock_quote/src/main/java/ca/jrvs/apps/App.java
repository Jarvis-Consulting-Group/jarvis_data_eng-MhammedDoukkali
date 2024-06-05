package ca.jrvs.apps;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private static final String API_KEY = "API KEY";
    private static final String API_HOST = "ALPHA VANTAGE API KEY";

    private static final String BASE_URL = "https://alpha-vantage.p.rapidapi.com/query";
    public static void main( String[] args )
    {
        String function = "TIME_SERIES_INTRADAY";
        String symbol = "MSFT";

        HttpRequest request = HttpRequest.newBuilder().build()
                        .uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol="+symbol+"&datatype=json"))


        logger.info( "Hello World!" );
    }
}
