package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

    @Mock
    HttpHelper mockHelper;

    @InjectMocks
    TwitterDao dao;


    @Test
    public void create() throws IOException {
        //test failed request
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

        //exception
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.create(postTweet);
            fail();

        } catch (RuntimeException e) {
        assertTrue(true);
        }

         String tweetJSON = "{"
                + " \"created_at\":\"Fri Apr 14 10:02:00 2023\",\n"
                + " \"id\":123,\n"
                + " \"id_str\":\"123\",\n"
                + " \"text\":\"test with loc322\","
                + " \"entities\":{\n"
                + "     \"hashtags\":[],"
                + "     \"user_mentions\":[]"
                + " },\n"
                + " \"coordinates\":null,\n"
                + " \"retweet_count\":0,\n"
                + " \"favorite_count\":0,\n"
                + " \"favorited\":false,\n"
                + " \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJSON, Tweet.class);
        //mock parseResponseBody
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweetTest = spyDao.create(postTweet);
        assertNotNull(tweetTest);
        assertNotNull(tweetTest.getText());
    }

    @Test
    public void findById() {
    }

    @Test
    public void deleteById() {
    }
}