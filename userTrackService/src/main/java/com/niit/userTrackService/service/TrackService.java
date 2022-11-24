package com.niit.userTrackService.service;

import com.niit.userTrackService.domain.Track;
import com.niit.userTrackService.domain.User;
import com.niit.userTrackService.exception.TrackNoteFoundException;
import com.niit.userTrackService.exception.UserAlreadyExistsException;
import com.niit.userTrackService.exception.UserNotFoundException;

import java.util.List;

public interface TrackService {
        User registerUser(User user) throws UserAlreadyExistsException;
        User saveUserTrackToList(Track track,String email) throws UserNotFoundException;
        User deleteUserTrackFromList(String email,int trackId)throws UserNotFoundException, TrackNoteFoundException;
        List<Track> getAllTrack(String email);
        User updateTrackForUser(String email, Track track) throws  UserNotFoundException;


}
