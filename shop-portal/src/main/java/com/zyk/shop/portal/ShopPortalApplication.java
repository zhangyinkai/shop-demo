package com.zyk.shop.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableZuulProxy
@EnableRedisHttpSession
@EnableHystrixDashboard
@SpringCloudApplication
public class ShopPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopPortalApplication.class, args);
    }

}
