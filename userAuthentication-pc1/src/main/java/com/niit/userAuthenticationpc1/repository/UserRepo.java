package com.niit.userAuthenticationpc1.repository;

import com.niit.userAuthenticationpc1.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users ,String> {
    public Users findByEmailIdAndPassword(String emailId,String password);
}
