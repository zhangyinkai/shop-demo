package com.zyk.shop.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ShopPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopPortalApplication.class, args);
    }

}
