package com.zaynukov.dev.angeldiary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyApplication {

    private static Logger logger = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MyApplication.class, args);
    }

}
