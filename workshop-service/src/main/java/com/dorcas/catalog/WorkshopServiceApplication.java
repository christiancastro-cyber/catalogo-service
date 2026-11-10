package com.dorcas.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WorkshopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkshopServiceApplication.class, args);
    }
}