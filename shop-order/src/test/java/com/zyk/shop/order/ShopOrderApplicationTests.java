package com.zyk.shop.order;

import com.zyk.shop.order.interceptor.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopOrderApplication.class)
public class ShopOrderApplicationTests {

    @Autowired
    private TokenService tokenService;

    @Test
    public void contextLoads() {
        System.out.println(tokenService.tokenValid("xxxx"));
    }

}
