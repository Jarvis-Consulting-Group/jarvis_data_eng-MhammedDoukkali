package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.TweetUtil;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TwitterControllerIntTest {

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";
    private TwitterDao dao;
    private TwitterHttpHelper twitterHttpHelper;
    private TwitterService service;

    private TwitterController controller;

    @Before
    public void setUp() throws Exception{
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");
        twitterHttpHelper =new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
        dao = new TwitterDao(twitterHttpHelper);
        service = new TwitterService(dao);
        controller = new TwitterController(service);
    }
    @Test
    public void postTweet() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        String[] args ={"post","tweet test", "111.6:45.8"};
        Tweet expectedTweet = TweetUtil.builtTweet("test tweet", -111.6, 45.8);
        System.out.println(expectedTweet);
        expectedTweet = controller.postTweet(args);
        System.out.println(expectedTweet);
        assertNotNull(expectedTweet);
        assertEquals(2, expectedTweet.getCoordinates().getCoordinates().size());
        assertEquals("@someone with random text", expectedTweet.getText());

    }

    @Test
    public void showTweet() {
        String[] args ={"show", "932586791953158144"};
        Tweet expectedTweet = TweetUtil.builtTweet("test tweet", -111.6, 45.8);
        String text = "";
        expectedTweet = controller.showTweet(args);
        System.out.println(expectedTweet.getText());
        assertNotNull(expectedTweet);
        assertEquals("932586791953158144", expectedTweet.getId());
        assertEquals(text, expectedTweet.getText());
    }

    @Test
    public void deleteTweet() {
        String[] args ={"delete","932586791953158144"};
        List<Tweet> deletedTweets = controller.deleteTweet(args);
        String text = "";
        Tweet tweet = deletedTweets.get(0);
        Double lat = 45.8;
        Double lon = -111.6;
        assertEquals(text, tweet.getText());
        assertEquals(2 , tweet.getCoordinates().getCoordinates().size());
        assertEquals(lon, tweet.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, tweet.getCoordinates().getCoordinates().get(1));

    }
}