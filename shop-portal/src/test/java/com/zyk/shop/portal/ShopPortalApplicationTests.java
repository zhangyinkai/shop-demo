package com.zyk.shop.portal;

import com.zyk.shop.portal.po.ShopUser;
import com.zyk.shop.portal.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopPortalApplication.class)
public class ShopPortalApplicationTests {

    @Autowired
    private IndexService indexService;

    @Test
    public void contextLoads() {
        ShopUser shopUser = new ShopUser();
        shopUser.setUserAlias("xxxx");
        shopUser.setLoginKey("123456");
        System.out.println(indexService.login(shopUser));
    }

}
