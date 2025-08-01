package com.enaa.brief;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
    public class BriefServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BriefServiceApplication.class, args);
    }

}
