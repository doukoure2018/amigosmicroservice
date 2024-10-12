package com.amigoscode.eurokoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurokaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurokaserverApplication.class,args);
    }
}
