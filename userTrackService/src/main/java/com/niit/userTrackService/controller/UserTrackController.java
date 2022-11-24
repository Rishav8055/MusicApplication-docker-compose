package com.niit.userTrackService.controller;

import com.niit.userTrackService.domain.Track;
import com.niit.userTrackService.domain.User;
import com.niit.userTrackService.exception.TrackNoteFoundException;
import com.niit.userTrackService.exception.UserAlreadyExistsException;
import com.niit.userTrackService.exception.UserNotFoundException;
import com.niit.userTrackService.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserTrackController {
    private TrackService trackService;
    private ResponseEntity responseEntity;
@Autowired
    public UserTrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @PostMapping("/register")
    public  ResponseEntity<?> register(@RequestBody User user) throws UserAlreadyExistsException{
        try {
            user.setTrackList(new ArrayList<>());
            return new ResponseEntity<>(trackService.registerUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        }
    }
    @PostMapping("/addtrack/{email}")
    public ResponseEntity<?> addTrackToUser(@PathVariable String email, @RequestBody Track track) throws UserNotFoundException{
    try {
return new ResponseEntity<>(trackService.saveUserTrackToList(track ,email),HttpStatus.OK);
    } catch (UserNotFoundException e) {
        throw new UserNotFoundException();
        }
    }
    @DeleteMapping("/deletetrack/{trackId}/{email}")
    public ResponseEntity<?> deleteUserProductFromList(@PathVariable int trackId, @PathVariable String email) throws UserNotFoundException, TrackNoteFoundException {
        try {
            responseEntity = new ResponseEntity<>(trackService.deleteUserTrackFromList(String.valueOf(email), trackId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (TrackNoteFoundException e) {
            throw new TrackNoteFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
