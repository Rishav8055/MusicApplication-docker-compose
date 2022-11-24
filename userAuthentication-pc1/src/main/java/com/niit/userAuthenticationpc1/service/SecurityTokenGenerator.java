package com.niit.userAuthenticationpc1.service;

import com.niit.userAuthenticationpc1.domain.Users;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String>generateToken(Users users);
}
