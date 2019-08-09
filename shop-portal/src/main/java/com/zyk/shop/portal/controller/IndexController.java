package com.zyk.shop.portal.controller;

import com.zyk.shop.portal.common.BaseResult;
import com.zyk.shop.portal.common.ResultState;
import com.zyk.shop.portal.po.ShopOrder;
import com.zyk.shop.portal.po.ShopUser;
import com.zyk.shop.portal.po.ShopUserResult;
import com.zyk.shop.portal.service.IndexService;
import com.zyk.shop.portal.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = {"/","/index","/index.html"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = {"/login","/login.html"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = {"/register","/register.html"}, method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }


    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView loginLogic(ShopUser shopUser, String code, HttpServletRequest request) {
        log.info("login logic ==> " + shopUser);
        ModelAndView modelAndView = new ModelAndView();
        BaseResult baseResult = indexService.login(shopUser);
        log.info("login logic <== " + baseResult);
        String redirect = "redirect:login";
        commonLogic(modelAndView, baseResult, redirect,request);
        return modelAndView;
    }

    private void commonLogic(ModelAndView modelAndView, BaseResult baseResult, String s,HttpServletRequest request) {
        if (baseResult != null && baseResult.getCode() == ResultState.success.getCode()) {
            if(baseResult instanceof ShopUserResult){
                ShopUserResult shopUserResult = (ShopUserResult)baseResult;
                ShopUser shopUser = shopUserResult.getBody();
                if(shopUser!=null){
                    log.info("存入user到session ==> "+shopUser);
                    request.getSession().setAttribute("user",shopUser);
                }
            }
            modelAndView.setViewName("redirect:index");
            return ;
        } else if (baseResult != null && baseResult.getCode() != ResultState.success.getCode()) {
            modelAndView.setViewName(s);
            modelAndView.addObject("error", baseResult.getMessage());
            return ;
        }
        modelAndView.setViewName(s);
        modelAndView.addObject("error","服务器开小差~");
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ModelAndView registerLogic(ShopUser shopUser, String code, HttpServletRequest request) {
        log.info("register logic ==> " + shopUser);
        ModelAndView modelAndView = new ModelAndView();
        BaseResult baseResult = indexService.register(shopUser);
        log.info("register logic <== " + baseResult);
        String redirect = "redirect:register";
        commonLogic(modelAndView, baseResult, redirect,request);
        return modelAndView;
    }

    @RequestMapping(value = {"/gouwuche","/gouwuche.html"}, method = RequestMethod.GET)
    public ModelAndView gouwuche() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gouwuche");
        return modelAndView;
    }

    @RequestMapping(value = {"/dingdanzhongxin","/dingdanzhongxin.html"}, method = RequestMethod.GET)
    public ModelAndView dingdanzhongxin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dingdanzhongxin");
        ShopUser shopUser = (ShopUser)request.getSession().getAttribute("user");
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setTokenId(shopUser.getTokenId());
        BaseResult baseResult = orderService.orderList(shopOrder);
        log.info("orderListResult <=="+baseResult);
        if(baseResult.getCode()==ResultState.success.getCode()){
            modelAndView.addObject("orderList",baseResult.getBody());
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/self_info","/self_info.html"}, method = RequestMethod.GET)
    public ModelAndView selfInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("self_info");
        return modelAndView;
    }

    @RequestMapping(value = {"/liebiao","/liebiao.html"}, method = RequestMethod.GET)
    public ModelAndView liebiao() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("liebiao");
        return modelAndView;
    }

    @RequestMapping(value = {"/xiangqing","/xiangqing.html"}, method = RequestMethod.GET)
    public ModelAndView xiangqing() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("xiangqing");
        return modelAndView;
    }

}
