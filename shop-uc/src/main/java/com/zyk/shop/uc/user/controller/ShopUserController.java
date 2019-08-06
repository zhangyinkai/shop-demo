package com.zyk.shop.uc.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyk.shop.uc.common.BaseResult;
import com.zyk.shop.uc.common.ResultState;
import com.zyk.shop.uc.user.entity.ShopUser;
import com.zyk.shop.uc.user.service.IShopUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhangyk
 * @since 2019-08-05
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class ShopUserController {

    @Autowired
    private IShopUserService iShopUserService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(ShopUser loginUser){
        log.info("login user >>> "+loginUser);
        BaseResult baseResult = new BaseResult();
        QueryWrapper<ShopUser> ew = new QueryWrapper();
        ShopUser queryUser = new ShopUser();
        queryUser.setUserAlias(loginUser.getUserAlias());
        ew.setEntity(queryUser);
        ShopUser shopUser = iShopUserService.getOne(ew);
        if(shopUser==null){
            baseResult.setCode(ResultState.error.getCode());
            baseResult.setMessage("用户不存在!");
            return baseResult;
        }
        String md5Key = DigestUtils.md5DigestAsHex(loginUser.getLoginKey().getBytes());
        String loginKey = DigestUtils.md5DigestAsHex((md5Key+shopUser.getSalt()).getBytes());
        if(!loginKey.equals(shopUser.getLoginKey())){
            baseResult.setCode(ResultState.error.getCode());
            baseResult.setMessage("登录密码有误!");
            return baseResult;
        }
        log.info("login user <<< "+baseResult);
        baseResult.setBody(shopUser);
        return baseResult;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult register(@RequestBody ShopUser shopUser){
        log.info("register user ==> "+shopUser);
        //查询用户是否存在
        BaseResult baseResult = new BaseResult();
        QueryWrapper<ShopUser> ew = new QueryWrapper();
        ShopUser queryUser = new ShopUser();
        queryUser.setUserAlias(shopUser.getUserAlias());
        ew.setEntity(queryUser);
        int size = iShopUserService.count(ew);
        if(size>0){
            baseResult.setCode(ResultState.error.getCode());
            baseResult.setMessage("用户已存在!");
            return baseResult;
        }
        //加密密码
        String md5Key = DigestUtils.md5DigestAsHex(shopUser.getLoginKey().getBytes());
        shopUser.setSalt(UUID.randomUUID().toString().toLowerCase());
        shopUser.setLoginKey(DigestUtils.md5DigestAsHex((md5Key+shopUser.getSalt()).getBytes()));
        shopUser.setCreateTime(new Date());
        iShopUserService.save(shopUser);
        log.info("register user end <== "+shopUser);
        baseResult.setBody(shopUser);
        return baseResult;
    }

}

