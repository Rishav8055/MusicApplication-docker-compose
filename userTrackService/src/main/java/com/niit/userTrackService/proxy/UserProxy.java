package com.niit.userTrackService.proxy;

import com.niit.userTrackService.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication-service",url = "http://authentication-service:9100/")
public interface UserProxy {
    @PostMapping("/userservice/register")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}
