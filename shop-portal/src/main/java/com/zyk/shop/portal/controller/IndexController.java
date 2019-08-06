package com.zyk.shop.portal.controller;

import com.zyk.shop.portal.common.BaseResult;
import com.zyk.shop.portal.common.ResultState;
import com.zyk.shop.portal.po.ShopUser;
import com.zyk.shop.portal.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController(value = "/")
public class IndexController {

    @Autowired
    private IndexService indexService;

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
    public ModelAndView loginLogic(ShopUser shopUser, String code) {
        log.info("login logic ==> " + shopUser);
        ModelAndView modelAndView = new ModelAndView();
        BaseResult baseResult = indexService.login(shopUser);
        log.info("login logic <== " + baseResult);
        if (commonLogic(modelAndView, baseResult, "redirect:login")) return modelAndView;
        modelAndView.setViewName("redirect:login");
        modelAndView.addObject("error","服务器开小差~");
        return modelAndView;
    }

    private boolean commonLogic(ModelAndView modelAndView, BaseResult baseResult, String s) {
        if (baseResult != null && baseResult.getCode() == ResultState.success.getCode()) {
            modelAndView.setViewName("redirect:index");
            return true;
        } else if (baseResult != null && baseResult.getCode() != ResultState.success.getCode()) {
            modelAndView.setViewName(s);
            modelAndView.addObject("error", baseResult.getMessage());
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ModelAndView registerLogic(ShopUser shopUser, String code) {
        log.info("register logic ==> " + shopUser);
        ModelAndView modelAndView = new ModelAndView();
        BaseResult baseResult = indexService.register(shopUser);
        log.info("register logic <== " + baseResult);
        if (commonLogic(modelAndView, baseResult, "redirect:register")) return modelAndView;
        modelAndView.setViewName("redirect:register");
        modelAndView.addObject("error","服务器开小差~");
        return modelAndView;
    }
}
