package com.niit.userTrackService.repository;

import com.niit.userTrackService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepo extends MongoRepository<User,String> {
        User findByEmail(String email);
}
