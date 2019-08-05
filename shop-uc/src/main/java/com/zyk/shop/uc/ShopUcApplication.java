package com.zyk.shop.uc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.zyk.shop.uc.*.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class ShopUcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopUcApplication.class, args);
    }

}
