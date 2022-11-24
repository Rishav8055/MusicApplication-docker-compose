package com.niit.userTrackService.domain;

import org.apache.catalina.LifecycleState;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class User {
    private int userId;
    @Id
    private String email;
    private String userName;
    List<Track> trackList;

    public User() {
    }

    public User(int userId, String email, String userName, List<Track> trackList) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.trackList = trackList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", trackList=" + trackList +
                '}';
    }
}
