package com.mvppoa.adidas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdidasCityItinerarySearchApp {

    public static void main(String[] args) {
        SpringApplication.run(AdidasCityItinerarySearchApp.class, args);
    }

}
