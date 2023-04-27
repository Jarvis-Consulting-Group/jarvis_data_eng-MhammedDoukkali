package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;

import java.util.List;

public class TwitterController implements Controller {

    private TwitterService tweetService;
    private CrdDao dao;

    public TwitterController(TwitterService tweetService) {
        this.tweetService = tweetService;
    }
    @Override
    public Tweet postTweet(String[] args) {
        // this methods have to be revised
        if(args == null || args.length == 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        String text = args[0];

        return (Tweet) dao.create(text);
    }

    @Override
    public Tweet showTweet(String[] args) {
        return null;
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        return null;
    }
}
