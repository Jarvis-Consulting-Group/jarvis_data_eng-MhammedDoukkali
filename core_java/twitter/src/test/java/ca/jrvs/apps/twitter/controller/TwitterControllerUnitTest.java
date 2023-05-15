package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

    private Tweet tweet;
    @Mock
    TwitterService mockService;

    @InjectMocks
    TwitterController twitterController;

    @Before
    public void setup() {
    twitterController = new TwitterController(mockService);
    }

    @Test
    public void postTweet() {
        String[] args ={"post","tweet test", "111.6:45.8"};
        Tweet expectedTweet = TweetUtil.builtTweet("test tweet", -74.0060, 40.7128);
        Mockito.when(mockService.postTweet(Mockito.any())).thenReturn(expectedTweet);
        expectedTweet = twitterController.postTweet(args);
        assertNotNull(expectedTweet);
    }

    @Test
    public void showTweet() {
        Mockito.when(mockService.showTweet(Mockito.any(), Mockito.any())).thenReturn(tweet);
        String[] args ={"show", "932586791953158144"};
        Tweet response = twitterController.showTweet(args);

        assertNotNull(response);
    }

    @Test
    public void deleteTweet() {
        Mockito.when(mockService.deleteTweets(Mockito.any())).thenReturn(new ArrayList<Tweet>());
        String[] args ={"delete","932586791953158144"};
        List<Tweet> deletedTweets = twitterController.deleteTweet(args);

        assertNotNull(deletedTweets);
    }
}
