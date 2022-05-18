package com.example.finalexam;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
class FinalexamApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(FinalexamApplicationTests.class);

    public static void main(String[] args) {

        SpringApplication.run(FinalexamApplicationTests.class, args);
    }

}
