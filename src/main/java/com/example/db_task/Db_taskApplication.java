package com.example.db_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Db_taskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Db_taskApplication.class, args);

        CreateEmailResponse createEmailResponse = new CreateEmailResponse();
        createEmailResponse.buildEmailResponse();
    }

}
