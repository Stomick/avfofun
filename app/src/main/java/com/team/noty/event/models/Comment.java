package com.team.noty.event.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment implements Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("surename")
    @Expose
    private String surename;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("userAvatar")
    @Expose
    private String userAvatar;
    @SerializedName("usrating")
    @Expose
    private String usrating;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("time")
    @Expose
    private String time;
    public final static Parcelable.Creator<Comment> CREATOR = new Creator<Comment>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        public Comment[] newArray(int size) {
            return (new Comment[size]);
        }

    }
            ;

    protected Comment(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.surename = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.userAvatar = ((String) in.readValue((String.class.getClassLoader())));
        this.usrating = ((String) in.readValue((String.class.getClassLoader())));
        this.rank = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Comment() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUsrating() {
        return usrating;
    }

    public void setUsrating(String usrating) {
        this.usrating = usrating;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(username);
        dest.writeValue(surename);
        dest.writeValue(userId);
        dest.writeValue(userAvatar);
        dest.writeValue(usrating);
        dest.writeValue(rank);
        dest.writeValue(rating);
        dest.writeValue(time);
    }

    public int describeContents() {
        return 0;
    }

}
