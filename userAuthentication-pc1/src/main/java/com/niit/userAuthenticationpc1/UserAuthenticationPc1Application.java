package com.niit.userAuthenticationpc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserAuthenticationPc1Application {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationPc1Application.class, args);
	}

}
