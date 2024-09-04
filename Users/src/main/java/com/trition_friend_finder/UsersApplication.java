package com.trition_friend_finder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class UsersApplication {

	@Value("${spring.data.mongodb.uri}")
    private String mongoUri;
	
	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}


    @PostConstruct
    public void printMongoUri() {
        System.out.println("MongoDB URI: " + mongoUri);
    }
}
