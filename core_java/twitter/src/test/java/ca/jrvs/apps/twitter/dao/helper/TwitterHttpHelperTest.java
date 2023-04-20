package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.*;

public class TwitterHttpHelperTest {

    private TwitterHttpHelper twitterHttpHelper;

    @Before
    public void setUp() throws Exception {
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");
        twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    }
    @Test
    public void httpPost() throws Exception{
        HttpResponse response = twitterHttpHelper.httpPost(new URI("https://api.publicapis.org/entries"));
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void httpGet() throws Exception{
        HttpResponse response = twitterHttpHelper.httpGet(new URI("https://api.publicapis.org/entries"));
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}