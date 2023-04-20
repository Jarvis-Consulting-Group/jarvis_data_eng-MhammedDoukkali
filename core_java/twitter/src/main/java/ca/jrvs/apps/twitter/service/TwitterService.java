package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TwitterService implements Service {

    private CrdDao dao;

    @Autowired
    public TwitterService(CrdDao dao) { this.dao = dao; }

    @Override
    public Tweet postTweet(Tweet tweet) {

        validatePostTweet(tweet);

        return (Tweet) dao.create(tweet);
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        return null;
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        return null;
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
}
