package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class TwitterDaoIntTest {

    private TwitterDao dao;
    @Before
    public void setUp() throws Exception {
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");
        System.out.println(CONSUMER_KEY+ "|"+ CONSUMER_SECRET + "|"+ ACCESS_TOKEN+ "|"+ TOKEN_SECRET);
        HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);

        //set up dependencies
        dao = new TwitterDao(httpHelper);
    }

    @Test
    public void create() {
        String hashTag = "#abc";
        String text = "@someone sometext" + hashTag + " " +System.currentTimeMillis();
        Double lat = 1d;
        Double lon =-1d;
        Tweet postTweet = new Tweet();
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(Arrays.asList(new Double[]{lat, lon}));
        coordinates.setType("");
        postTweet.setText(text);
        postTweet.setCoordinates(coordinates);
        Tweet tweet;
        tweet = dao.create(postTweet);

        assertEquals(text, tweet.getText());

        assertNotNull(tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getCoordinates().size());
        assertEquals(Optional.of(1d), tweet.getCoordinates().getCoordinates().get(0));
        assertEquals(Optional.of(-1d), tweet.getCoordinates().getCoordinates().get(1));

    }

    @Test
    public void findById() {
    }

    @Test
    public void deleteById() {
    }
}