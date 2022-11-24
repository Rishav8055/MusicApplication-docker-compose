package com.niit.userAuthenticationpc1.service;

import com.niit.userAuthenticationpc1.domain.Users;
import com.niit.userAuthenticationpc1.exception.UserNotFoundException;
import com.niit.userAuthenticationpc1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Users saveUser(Users users) {
        return userRepo.save(users);
    }

    @Override
    public Users findByEmailIdAndPassword(String emailId, String password)throws UserNotFoundException {
        Users users = userRepo.findByEmailIdAndPassword(emailId,password);
        if (users==null){
            throw new UserNotFoundException();
        }
        return users;
    }

    @Override
    public List<Users> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public boolean deleteUser(String emailId) throws UserNotFoundException {
        if (userRepo.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }else{
            userRepo.deleteById(emailId);
        }
        return true;
    }
}
