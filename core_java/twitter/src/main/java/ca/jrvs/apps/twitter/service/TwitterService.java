package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

    private CrdDao dao;

    @Autowired
    public TwitterService(CrdDao dao) { this.dao = dao; }

    @Override
    public Tweet postTweet(Tweet tweet) {
        validatePostTweet(tweet);

//        if(dao == null) {
//            throw new NullPointerException("DAO is null");
//        }

        return (Tweet) dao.create(tweet);
    }

    public Tweet showTweet(String id, String[] fields) {

        Tweet getTheTweet = (Tweet) dao.findById(id);
        if (fields != null) {
        Tweet theTweetWithNull = new Tweet();

        // Set the fields that are not null on the tweet to be returned
            for(String field : fields) {
                switch (field) {
                    case "created_at":
                        theTweetWithNull.setCreated_at(getTheTweet.getCreated_at());
                        break;
                    case "id":
                        theTweetWithNull.setId(getTheTweet.getId());
                        break;
                    case "id_str":
                        theTweetWithNull.setId_str(getTheTweet.getId_str());
                        break;
                    case "text":
                        theTweetWithNull.setText(getTheTweet.getText());
                        break;
                    case "entities":
                        theTweetWithNull.setEntities(getTheTweet.getEntities());
                        break;
                    case "hashtags":
                        theTweetWithNull.setHashtag(getTheTweet.getHashtag());
                        break;
                    case "user_mentions":
                        theTweetWithNull.setUserMention(getTheTweet.getUserMention());
                        break;
                    case "coordinates":
                        theTweetWithNull.setCoordinates(getTheTweet.getCoordinates());
                        break;
                    case "retweet_count":
                        theTweetWithNull.setRetweet_count(getTheTweet.getRetweet_count());
                        break;
                    case "favorite_count":
                        theTweetWithNull.setFavorite_count(getTheTweet.getFavorite_count());
                        break;
                    case "retweeted":
                        theTweetWithNull.setRetweeted(getTheTweet.getRetweeted());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid field: " + field);
                }
            }
            return theTweetWithNull;
        }
        return getTheTweet;
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> deletedTweets = new ArrayList<>();
        for(String id : ids){
            Tweet tweet = (Tweet) dao.findById(id);
            if(tweet == null) {
                throw new IllegalArgumentException("Invalid tweet ID: " +id);
            }
            dao.deleteById(id);
            deletedTweets.add(tweet);
        }
        return deletedTweets;
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
