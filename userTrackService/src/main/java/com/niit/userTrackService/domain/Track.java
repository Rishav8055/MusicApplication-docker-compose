package com.niit.userTrackService.domain;

import org.springframework.data.annotation.Id;

public class Track {
    @Id
    private int trackId;
    private String trackName;
    private int artistId;
    private String artistName;

    public Track() {
    }

    public Track(int trackId, String trackName, int artistId, String artistName) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
