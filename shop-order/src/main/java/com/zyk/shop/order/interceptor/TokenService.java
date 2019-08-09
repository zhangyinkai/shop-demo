package com.zyk.shop.order.interceptor;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zyk.shop.order.common.BaseResult;
import com.zyk.shop.order.common.ResultState;
import com.zyk.shop.order.config.AppConfig;
import com.zyk.shop.order.po.ShopUserResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class TokenService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AppConfig appConfig;

    @HystrixCommand(fallbackMethod = "fallBack")
    public BaseResult tokenValid(String tokenId){
       return restTemplate.getForObject(appConfig.getUcUrl()+"/user/tokenValid?tokenId={1}", ShopUserResult.class,tokenId);
    }

    public BaseResult fallBack(String tokenId,Throwable throwable) {
        log.error(throwable.getMessage(),throwable);
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(ResultState.error.getCode());
        baseResult.setMessage("请求超时~");
        return baseResult;
    }
}
