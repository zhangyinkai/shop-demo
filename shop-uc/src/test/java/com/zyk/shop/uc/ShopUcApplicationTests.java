package com.zyk.shop.uc;

import com.zyk.shop.uc.common.TokenUtil;
import com.zyk.shop.uc.user.entity.ShopUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopUcApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private TokenUtil tokenUtil;

    @Test
    public void contextLoads() {

        //添加对象
        ShopUser shopUser = new ShopUser();
        shopUser.setId(123456L);
        RBucket<ShopUser> userRBucket = redissonClient.getBucket("test_user");
        userRBucket.set(shopUser,30, TimeUnit.SECONDS);

        //获取所有key
        redissonClient.getKeys().getKeys().forEach((key)-> System.out.println(key));
        //shopUser.setId(456789L);
       //userRBucket.set(shopUser);

        System.out.println(redissonClient.getBucket("test_user").get());
        long l = userRBucket.remainTimeToLive();
        System.out.println("l ===> "+l);

        //删除
        //userRBucket.delete();

        redissonClient.getKeys().getKeys().forEach((key)-> System.out.println(key));
    }

    @Test
    public void testLock(){
        RLock lock = redissonClient.getLock("gettoken");
        lock.lock();//获取锁
        //执行业务


        lock.unlock();//释放锁

    }

    @Test
    public void testToken(){
        String tokenId = TokenUtil.getToken();
        ShopUser shopUser = new ShopUser();
        shopUser.setId(123456L);
        shopUser.setTokenId(tokenId);
        shopUser.setLoginKey("loginkey");
        shopUser.setSalt("salt");
        tokenUtil.saveToken(shopUser);
        log.info("cache save shopUser ==> "+shopUser);
        log.info("cache get shopUser <== "+tokenUtil.getUserByTokenId(tokenId));
        //tokenUtil.delToken(tokenId);
        log.info("token exist ==> "+tokenUtil.existToken(tokenId));
    }


}
