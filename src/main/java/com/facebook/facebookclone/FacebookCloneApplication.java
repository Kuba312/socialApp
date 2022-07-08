package com.facebook.facebookclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

public class FacebookCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacebookCloneApplication.class, args);
    }

}
