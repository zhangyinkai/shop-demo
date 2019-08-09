package com.zyk.shop.order.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyk.shop.order.common.BaseResult;
import com.zyk.shop.order.common.ResultState;
import com.zyk.shop.order.po.ShopUser;
import com.zyk.shop.order.po.ShopUserResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("token interceptor check ...");
        String tokenId = request.getHeader("tokenId");
        if(StringUtils.isEmpty(tokenId)){
            sendMessage(response,"用户未登录");
            return false;
        }
        BaseResult baseResult = tokenService.tokenValid(tokenId);
        log.info("tokenValid <== "+baseResult);
        if(baseResult.getCode()== ResultState.success.getCode()&& baseResult instanceof ShopUserResult){
            ShopUser shopUser = ((ShopUserResult)baseResult).getBody();
            request.setAttribute("shopUser",shopUser);
            return true;
        }
        sendMessage(response,"用户未登录");
        return false;
    }

    private void sendMessage(HttpServletResponse response,String message) throws Exception{
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(ResultState.error.getCode());
        baseResult.setMessage(message);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        printWriter.println(mapper.writeValueAsString(baseResult));
        printWriter.flush();
        printWriter.close();
    }
}
