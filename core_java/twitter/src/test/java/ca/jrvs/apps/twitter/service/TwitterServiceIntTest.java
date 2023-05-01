package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;

public class TwitterServiceIntTest {


    @Test
    public void postTweet(Tweet tweet) {
    // tester if the tweet has a business logic which is
        // 140 length and lat and lon respecting the convention

        validatePostTweet(tweet);

    }
    private void validatePostTweet(Tweet tweet) {
        final int MAX_CHARS = 140;

        if(tweet.getText().length() > MAX_CHARS) {
            throw new IllegalArgumentException("Tweet text exceeds" + MAX_CHARS + "characters.");
        }
        double lat = tweet.getCoordinates().getCoordinates().get(0);
        double lon = tweet.getCoordinates().getCoordinates().get(1);

        if(lat < -90 || lat > 90) {
            throw new IllegalArgumentException("Latitude out of range (-90 to 90).");
        }

        if(lon < -180 || lon > 180) {
            throw new IllegalArgumentException("Longitude out of range (-180 to 180).");
        }
    }

    @Test
    public void showTweet() {
    }

    @Test
    public void deleteTweets() {
    }
}