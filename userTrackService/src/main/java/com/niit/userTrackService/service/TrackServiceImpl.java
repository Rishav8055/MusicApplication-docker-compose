package com.niit.userTrackService.service;

import com.niit.userTrackService.domain.Track;
import com.niit.userTrackService.domain.User;
import com.niit.userTrackService.exception.TrackNoteFoundException;
import com.niit.userTrackService.exception.UserAlreadyExistsException;
import com.niit.userTrackService.exception.UserNotFoundException;
import com.niit.userTrackService.proxy.UserProxy;
import com.niit.userTrackService.repository.TrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {
    private UserProxy userProxy;

    private TrackRepo trackRepo;

    @Autowired

    public TrackServiceImpl(UserProxy userProxy, TrackRepo trackRepo) {
        this.userProxy = userProxy;
        this.trackRepo = trackRepo;
    }


    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if (trackRepo.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User  savedUser = trackRepo.save(user);
        if(!(savedUser.getEmail().isEmpty())){
            ResponseEntity r =userProxy.saveUser(user);
            System.out.println(r.getBody());
        }
        return savedUser;
    }

    @Override
    public User saveUserTrackToList(Track track, String email) throws UserNotFoundException {
        if (trackRepo.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = trackRepo.findByEmail(email);
        if (user.getTrackList() == null) {
            user.setTrackList(Arrays.asList(track));
        } else {
            List<Track> list = user.getTrackList();
            list.add(track);
            user.setTrackList(list);
        }
        return trackRepo.save(user);
    }

    @Override
    public User deleteUserTrackFromList(String email, int trackId) throws UserNotFoundException, TrackNoteFoundException {
        boolean trackIdIsPresent = false;
        if (trackRepo.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = trackRepo.findById(email).get();
        List<Track> tracks = user.getTrackList();
        trackIdIsPresent = tracks.removeIf(x -> x.getTrackId() == trackId);
        if (!trackIdIsPresent) {
            throw new TrackNoteFoundException();
        }

        return trackRepo.save(user);
    }

    @Override
    public List<Track> getAllTrack(String email) {
        return trackRepo.findById(email).get().getTrackList();
    }

    @Override
    public User updateTrackForUser(String email, Track track) throws UserNotFoundException {
        if (trackRepo.findById(email).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = trackRepo.findById(email).get();
        List<Track> tracks = user.getTrackList();
        Iterator<Track> iterator = tracks.iterator();
        while (iterator.hasNext()) {
            Track track1 = iterator.next();
            if (track1.getTrackId() == track.getTrackId()) {
                track1.setTrackName(track.getTrackName());
                track1.setArtistName(track.getArtistName());
            }
        }
        user.setTrackList(tracks);


        return trackRepo.save(user);
    }
}
