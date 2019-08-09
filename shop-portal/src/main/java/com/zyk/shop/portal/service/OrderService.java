package com.zyk.shop.portal.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zyk.shop.portal.common.BaseResult;
import com.zyk.shop.portal.common.ResultState;
import com.zyk.shop.portal.config.AppConfig;
import com.zyk.shop.portal.po.ShopOrder;
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

import java.math.BigDecimal;
import java.util.HashMap;

@Slf4j
@Service
public class OrderService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AppConfig appConfig;

    @HystrixCommand(fallbackMethod = "fallBack")
    public BaseResult order(ShopOrder shopOrder) {
        //提交from表单
        HttpHeaders headers = new HttpHeaders();
        headers.add("tokenId",shopOrder.getTokenId());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<ShopOrder> requestEntity = new HttpEntity<ShopOrder>(shopOrder, headers);
        return restTemplate.postForEntity(appConfig.getOrderUrl()+"/order/order",requestEntity, BaseResult.class).getBody();
    }

    public BaseResult fallBack(ShopOrder shopOrder,Throwable throwable) {
        log.error(throwable.getMessage(),throwable);
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(ResultState.error.getCode());
        baseResult.setMessage("服务开小差~");
        return baseResult;
    }

    @HystrixCommand(fallbackMethod = "fallBack")
    public BaseResult orderList(ShopOrder shopOrder){
        // 提交body
        HttpHeaders headers = new HttpHeaders();
        headers.add("tokenId",shopOrder.getTokenId());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<ShopOrder> requestEntity = new HttpEntity<ShopOrder>(shopOrder, headers);
        return restTemplate.postForEntity(appConfig.getOrderUrl()+"/order/list",requestEntity, BaseResult.class).getBody();

    }

}
