package com.comments.commentSystem;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.comments.commentSystem.repository"})
public class CommentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentSystemApplication.class, args);
    }



}

