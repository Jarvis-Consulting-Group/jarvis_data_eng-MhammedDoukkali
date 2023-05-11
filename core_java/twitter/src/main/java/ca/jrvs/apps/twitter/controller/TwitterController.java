package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;

import org.springframework.util.StringUtils;

import java.util.List;

public class TwitterController implements Controller {

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";
    private Service service;

    public TwitterController(Service service) {
        this.service = service;
    }
    @Override
    public Tweet postTweet(String[] args) {
        // this methods have to be revised
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }
        String tweet_text = args[1];
        String coord = args[2];
        String[] coordArray = coord.split(COORD_SEP);

        if (coordArray.length != 2 || StringUtils.isEmpty(tweet_text)) {
            throw new IllegalArgumentException(
                    "Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\""
            );
        }
        Double lon = null;
        Double lat = null;
            try {
                lat = Double.parseDouble(coordArray[0]);
                lon = Double.parseDouble(coordArray[1]);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\""
                        , e);
            }

        Tweet newTweet = TweetUtil.builtTweet(tweet_text, lon, lat);


            return service.postTweet(newTweet);
    }

    @Override
    public Tweet showTweet(String[] args) {
        if(args.length != 2 && args.length != 3) {
            throw new IllegalArgumentException(
                    "USAGE: TwitterCLIApp show \"tweet_id\" \"fields1, fields2, fields3...\""
            );
        }
        String id = args[1];
        String[] fields = null;
        if(args.length == 3) {
            if(StringUtils.isEmpty(args[2])) {
                throw new IllegalArgumentException(
                        "USAGE: TwitterCLIApp show \"tweet_id\" \"fields1, fields2, fields3...\""
                );
            }
            fields = args[2].split(COMMA);
        }
        return service.showTweet(id, fields);
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        if (args.length != 2)
            throw new IllegalArgumentException(
                    "USAGE: TwitterCLIApp delete \"[id1, id2,...]\""
            );
            String[] ids = args[1].split(COMMA);

        return service.deleteTweets(ids);

    }
}
