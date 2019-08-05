package com.zyk.shop.uc.user.controller;


import com.zyk.shop.uc.user.service.IShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhangyk
 * @since 2019-08-05
 */
@RestController
@RequestMapping("/user/shop-user")
public class ShopUserController {

    @Autowired
    private IShopUserService iShopUserService;

    @RequestMapping(value = "/list")
    public String list(){
        int size = iShopUserService.count();
        return "总行数："+size;
    }

}

