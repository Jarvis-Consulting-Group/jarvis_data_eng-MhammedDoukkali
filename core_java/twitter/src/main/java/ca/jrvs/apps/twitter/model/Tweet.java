package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "created_at",
        "id",
        "id_str",
        "text",
        "entities",
        "hashtags",
        "user_mentions",
        "coordinates",
        "retweet_count",
        "favorite_count",
        "favorited",
        "retweeted"
})


public class Tweet {

    @JsonPropertyOrder("created_at")
    private String created_at;
    @JsonPropertyOrder("id")
    private Long id;
    @JsonPropertyOrder("id_str")
    private String id_str;
    @JsonPropertyOrder("text")
    private String text;
    @JsonPropertyOrder(" entities")
    private Entities entities;
    @JsonPropertyOrder("hashtags")
    private Hashtag hashtag;
    @JsonPropertyOrder("user_mentions")
    private UserMention userMention;
    @JsonPropertyOrder("coordinates")
    private Coordinates coordinates;
    @JsonPropertyOrder("retweet_count")
    private String retweet_count;
    @JsonPropertyOrder("favorite_count")
    private String favorite_count;
    @JsonPropertyOrder("retweeted")
    private String retweeted;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public Hashtag getHashtag() {
        return hashtag;
    }

    public void setHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
    }

    public UserMention getUserMention() {
        return userMention;
    }

    public void setUserMention(UserMention userMention) {
        this.userMention = userMention;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(String retweet_count) {
        this.retweet_count = retweet_count;
    }

    public String getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(String favorite_count) {
        this.favorite_count = favorite_count;
    }

    public String getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(String retweeted) {
        this.retweeted = retweeted;
    }



}
