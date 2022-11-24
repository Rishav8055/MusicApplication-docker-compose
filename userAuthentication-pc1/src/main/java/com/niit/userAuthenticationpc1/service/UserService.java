package com.niit.userAuthenticationpc1.service;

import com.niit.userAuthenticationpc1.domain.Users;
import com.niit.userAuthenticationpc1.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    public Users saveUser(Users users);
    public Users findByEmailIdAndPassword(String emailId,String password) throws UserNotFoundException;
    List<Users> getAllUser();
    boolean deleteUser(String emailId) throws UserNotFoundException;

}
