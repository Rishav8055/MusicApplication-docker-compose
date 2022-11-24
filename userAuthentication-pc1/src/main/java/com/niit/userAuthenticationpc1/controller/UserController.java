package com.niit.userAuthenticationpc1.controller;

import com.niit.userAuthenticationpc1.domain.Users;
import com.niit.userAuthenticationpc1.exception.UserNotFoundException;
import com.niit.userAuthenticationpc1.service.SecurityTokenGenerator;
import com.niit.userAuthenticationpc1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

        private UserService userService;
        private SecurityTokenGenerator securityTokenGenerator;
        private ResponseEntity responseEntity;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }
        @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody Users users){
        Users createdUser=userService.saveUser(users);
        return responseEntity=new ResponseEntity<>("user created", HttpStatus.CREATED);

        }
        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody Users users) throws UserNotFoundException{
            Map<String,String> map=null;
            try {
                    Users users1 =userService.findByEmailIdAndPassword(users.getEmailId(),users.getPassword());
                    if (users1.getEmailId().equals(users.getEmailId())){
                        map=securityTokenGenerator.generateToken(users);
                    }
                    responseEntity=new ResponseEntity<>(map,HttpStatus.OK);
            } catch (UserNotFoundException e) {
                throw new UserNotFoundException();
            }catch (Exception e){
                responseEntity=new ResponseEntity<>("Try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
            }


            return responseEntity;
        }
        @GetMapping("/api/v1/userauth/users")
    public ResponseEntity<?> fetchAllUser() throws UserNotFoundException{
            List<Users> list=userService.getAllUser();
            responseEntity=new ResponseEntity<>(list,HttpStatus.OK);

            return responseEntity;
        }
        @DeleteMapping("/api/v1/userauth/{emailId}")
    public ResponseEntity<?> deleteEmailId(@PathVariable("emailId") String emailId) throws UserNotFoundException{
        ResponseEntity responseEntity1 =null;
        try {
            userService.deleteUser(emailId);
            responseEntity1=new ResponseEntity<>("SuccsesFully deleted 1 record",HttpStatus.OK);


        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }catch (Exception e){
            throw new UserNotFoundException();
        }
            return responseEntity1;
        }
}
