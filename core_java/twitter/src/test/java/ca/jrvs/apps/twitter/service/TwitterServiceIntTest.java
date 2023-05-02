package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TwitterServiceIntTest {

    private TwitterHttpHelper twitterHttpHelper;
    private TwitterService twitterService;
    private TwitterDao dao;
    private Tweet tweet;
    private CrdDao mockDao;

    @Before
    public void setUp()  {
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");
        twitterHttpHelper =new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
        twitterService = new TwitterService(dao);

        this.tweet = new Tweet();

    }
    @Test
    public void postTweet() {
    // tester if the tweet has a business logic which is
        // 140 length and lat and lon respecting the convention
        String text = "Test tweet";
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(Arrays.asList(45.508888, -73.561668));
        tweet.setText(text);
        tweet.setCoordinates(coordinates);

        Tweet createdTweet = twitterService.postTweet(tweet);

        assertEquals(text, createdTweet.getText());
        assertEquals(coordinates, createdTweet.getCoordinates());
    }


    @Test
    public void showTweet() {
        String id = "210462857140252672";
        String[] fields = {"created_at", "id", "id_str", "text"};
        Tweet tweet = new Tweet();
        tweet.setId_str(id);
        tweet.setText("Test tweet");

        Tweet result = twitterService.showTweet((id), new String[]{"text"});
        assertTrue(result.getCreated_at() == null);
        assertEquals(tweet.getId(), result.getId());
        assertEquals(tweet.getId_str(), result.getId_str());
        assertEquals(tweet.getText(), result.getText());
        assertTrue(result.getEntities() == null);
        assertTrue(result.getHashtag() == null);
        assertTrue(result.getUserMention() == null);
        assertTrue(result.getCoordinates() == null);
        assertTrue(result.getRetweet_count() == null);
        assertTrue(result.getFavorite_count() == null);
        assertTrue(result.getRetweeted() == null);

    }

    @Test
    public void deleteTweets() {
        String id1 = "210462857140252672";
        String id2 = "987654321324464654";
        String[] ids = {id1, id2};
        Tweet tweet1 = new Tweet();
        tweet.setId(Long.valueOf(id1));
        Tweet tweet2 = new Tweet();
        tweet2.setId(Long.valueOf(id2));

        when(mockDao.findById(id1)).thenReturn(tweet1);
        when(mockDao.findById(id2)).thenReturn(tweet2);
        List<Tweet> deletedTweets = twitterService.deleteTweets(ids);

        assertEquals(2, deletedTweets.size());
        assertEquals(id1, deletedTweets.get(0).getId());
        assertEquals(id2, deletedTweets.get(1).getId());

    }
}