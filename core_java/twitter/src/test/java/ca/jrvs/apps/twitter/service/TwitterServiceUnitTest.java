package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

    @Mock
    CrdDao mockDao;

    @InjectMocks
    private TwitterService twitterService;
    private Tweet tweet;

    @Before
    public void setUp() {
        tweet = new Tweet();
        tweet.setId_str("102030");
        tweet.setText("test tweet");
        tweet.setCoordinates(new Coordinates());

    }
    @Test
    public void postTweet() {
        // mock dao's create method
        when(mockDao.create(tweet)).thenReturn(tweet);

        //call the service method
        Tweet createdTweet = twitterService.postTweet(tweet);

        //assert that the tweet returned by the Twitter service is the same as the one returned by dao
        assertEquals(tweet, createdTweet);
    }

    @Test
    public void showTweet() {
        //mock dao's findById method
        when(mockDao.findById(anyString())).thenReturn(tweet);

        // call the service method
        Tweet foundTweet = twitterService.showTweet(String.valueOf(tweet.getId_str()), null);

        // assert that the tweet returned by the service is the same as the one returned by dao
        assertEquals(tweet, foundTweet);
    }

    @Test
    public void deleteTweets() {
        String[] ids = {"123", "456"};

        Tweet tweet1 = new Tweet();
        tweet1.setId(Long.valueOf("123"));

        Tweet tweet2 = new Tweet();
        tweet2.setId(Long.valueOf("456"));

        Mockito.when(mockDao.findById("123")).thenReturn(tweet1);
        Mockito.when(mockDao.findById("456")).thenReturn(tweet2);

        List<Tweet> deletedTweets = twitterService.deleteTweets(ids);

        assertEquals(2, deletedTweets.size());
        assertEquals(tweet1.getId(), deletedTweets.get(0).getId());
        assertEquals(tweet2.getId(), deletedTweets.get(1).getId());
        // mock dao's findById and deleteById methods
//        when(mockDao.findById(anyString())).thenReturn(tweet);
//        when(mockDao.deleteById(anyString())).thenReturn(tweet);

        // call the service method
//        List<Tweet> deletedTweets = twitterService.deleteTweets();
    }
}