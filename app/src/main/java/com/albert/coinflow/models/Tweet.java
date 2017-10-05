package com.albert.coinflow.models;

import org.parceler.Parcel;

@Parcel
public class Tweet {
    private String text;
    private String retweeted;
    private String name;
    private int followers;
    private String imageUrl;
    private int retweets;
    private String pushId;
    String index;

    public Tweet(String text, String retweeted, String name, int followers, String imageUrl, int retweets) {
        this.text = text;
        this.retweeted = retweeted;
        this.name = name;
        this.followers = followers;
        this.imageUrl = getLargeImageUrl(imageUrl);
        this.retweets = retweets;
        this.index = "not_specified";
    }

    public Tweet() {}

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public int getFollowers() {
        return followers;
    }

    public String getRetweeted() {
        return retweeted;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getRetweets() {
        return retweets;
    }

    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 11).concat(".jpg");
        return largeImageUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
