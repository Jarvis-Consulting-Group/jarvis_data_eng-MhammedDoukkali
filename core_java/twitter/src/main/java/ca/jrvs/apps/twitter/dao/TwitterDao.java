package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

@Repository
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

    @Override
    public Tweet findById(String s) {

        return null;
    }

    @Override
    public Tweet deleteById(String s) {

        return null;
    }

    private String getCreateUri(Tweet tweet) {
    String uriString = API_BASE_URI + POST_PATH + QUERY_SYM;

    PercentEscaper percentEscaper = new PercentEscaper("", false);

        uriString += "status" + EQUAL + percentEscaper.escape(tweet.getText());
        uriString += AMPERSAND + "lat" + EQUAL + tweet.getCoordinates().getCoordinates().get(0);
        uriString += AMPERSAND + "long" + EQUAL + tweet.getCoordinates().getCoordinates().get(1);

        return uriString;
    }

    Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    Tweet tweet = null;

    // Check response status
    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
        try {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            System.out.println("Response has no entity");
        }
        throw new RuntimeException("Unexpected response status: " + status);
    }

    if(response.getEntity() == null) {
        throw new RuntimeException("Empty response body");
    }

    // Convert response to str
    String jsonStr;
    try {
        jsonStr = EntityUtils.toString(response.getEntity());

    } catch (IOException e){
        throw new RuntimeException("Failed to convert entity to String", e);
    }

    //Deser JSON string to Tweet Object
        try {
        tweet = JsonParser.toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e) {
        throw new RuntimeException("Unable to convert JSON str to Object ", e);
        }

    return tweet;
    }

}
