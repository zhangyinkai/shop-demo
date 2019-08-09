package com.zyk.shop.order.common;

import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtil {

    public static String getOrderNo(Long userId){
        Assert.notNull(userId, "user id is required");
        return getOrderNo()+userId;
    }
    public static String getOrderNo(){
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }
}
