package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class TwitterDao implements CrdDao<Tweet, String> {

    //URI constants
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";
    //URI symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    //Response code
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;

    @Autowired
    public TwitterDao(HttpHelper httpHelper) { this.httpHelper = httpHelper; }

    @Override
    public Tweet create(Tweet entity) {
        // Construct URI
        String uriString;
        try {
            uriString = getCreateUri(entity);
            URI uri = new URI(uriString);
            //Execute HTTP request
            HttpResponse response = httpHelper.httpPost(uri);
            //Validate response and deser response to Tweet object
            return parseResponseBody(response, HTTP_OK);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid tweet input", e);
        }
        
    }

    private String getCreateUri(Tweet tweet) {
    String uriString = API_BASE_URI + POST_PATH + QUERY_SYM;

    PercentEscaper percentEscaper = new PercentEscaper("", false);

        uriString+= "status" + EQUAL + percentEscaper.escape(tweet.getText());
        uriString += AMPERSAND + "lat" + EQUAL + tweet.getCoordinates().getCoordinates().get(0);
    }

    private Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {

    }

}
