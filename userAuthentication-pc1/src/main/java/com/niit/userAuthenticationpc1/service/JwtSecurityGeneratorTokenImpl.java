package com.niit.userAuthenticationpc1.service;

import com.niit.userAuthenticationpc1.domain.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JwtSecurityGeneratorTokenImpl implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(Users users) {
        String jwtToken=null;
        jwtToken= Jwts.builder().setSubject(users.getEmailId()).
                setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "mykey").compact();
        Map<String,String>map =new HashMap<>();
        map.put("token",jwtToken);
        map.put("message","USER SUCCSESSFULLY LOGGED IN");
        return map;
    }
}
