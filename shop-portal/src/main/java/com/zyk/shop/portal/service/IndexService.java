package com.zyk.shop.portal.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zyk.shop.portal.common.BaseResult;
import com.zyk.shop.portal.common.ResultState;
import com.zyk.shop.portal.config.AppConfig;
import com.zyk.shop.portal.po.ShopUser;
import com.zyk.shop.portal.po.ShopUserResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class IndexService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AppConfig appConfig;

    @HystrixCommand(fallbackMethod = "fallBack")
    public BaseResult login(ShopUser shopUser) {
        //提交from表单
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("userAlias",shopUser.getUserAlias());
        params.add("loginKey",shopUser.getLoginKey());
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        return restTemplate.postForEntity(appConfig.getUcUrl()+"/user/login",requestEntity, ShopUserResult.class).getBody();
    }

    public BaseResult fallBack(ShopUser shopUser,Throwable throwable) {
        log.error(throwable.getMessage(),throwable);
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(ResultState.error.getCode());
        baseResult.setMessage("请求超时~");
        return baseResult;
    }

    @HystrixCommand(fallbackMethod = "fallBack")
    public BaseResult register(ShopUser shopUser) {
        // 提交body
        return restTemplate.postForObject(appConfig.getUcUrl()+"/user/register",shopUser,ShopUserResult.class);
    }


    @HystrixCommand(fallbackMethod = "fallBack")
    public BaseResult checkSession(ShopUser shopUser) {
        // get 请求
        return new BaseResult().setMessage(restTemplate.getForObject(appConfig.getUcUrl()+"/user/checkSession",String.class));
    }
}
