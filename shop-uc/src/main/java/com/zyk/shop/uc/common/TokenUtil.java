package com.zyk.shop.uc.common;

import com.zyk.shop.uc.user.entity.ShopUser;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtil {
    private final static String tokenKey="token_";
    @Autowired
    private RedissonClient redissonClient;

    public static String getToken(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    //保存token 有效期3天
    public void saveToken(ShopUser shopUser){
        RBucket<ShopUser> shopUserRBucket = redissonClient.getBucket(tokenKey+shopUser.getTokenId());
        shopUserRBucket.set(shopUser,3, TimeUnit.DAYS);//3天
    }

    //获取用户信息
    public ShopUser getUserByTokenId(String tokenId){
        RBucket<ShopUser> shopUserRBucket = redissonClient.getBucket(tokenKey+tokenId);
        return shopUserRBucket.get();
    }

    //判断是否存在
    public boolean existToken(String tokenId){
        RBucket<ShopUser> shopUserRBucket = redissonClient.getBucket(tokenKey+tokenId);
        return shopUserRBucket.get()!=null;
    }

    //删除token缓存
    public boolean delToken(String tokenId){
        RBucket<ShopUser> shopUserRBucket = redissonClient.getBucket(tokenKey+tokenId);
        return shopUserRBucket.delete();
    }
}
