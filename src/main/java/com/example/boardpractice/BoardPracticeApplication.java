package com.example.boardpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BoardPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoardPracticeApplication.class, args);
    }
}
