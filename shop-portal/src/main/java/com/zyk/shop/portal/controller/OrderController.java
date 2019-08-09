package com.zyk.shop.portal.controller;

import com.zyk.shop.portal.common.BaseResult;
import com.zyk.shop.portal.common.ResultState;
import com.zyk.shop.portal.po.ShopOrder;
import com.zyk.shop.portal.po.ShopUser;
import com.zyk.shop.portal.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order.do", method = RequestMethod.POST)
    public ModelAndView order(ShopOrder shopOrder, HttpServletRequest request) {
        log.info("shopOrder  order ==> " + shopOrder);
        ModelAndView modelAndView = new ModelAndView();
        ShopUser shopUser = (ShopUser)request.getSession().getAttribute("user");
        shopOrder.setTokenId(shopUser.getTokenId());
        String redirect = "redirect:/dingdanzhongxin";
        BaseResult baseResult = orderService.order(shopOrder);
        log.info("shopOrder order <== " + baseResult);
        if(baseResult.getCode()== ResultState.error.getCode()){
            redirect = "redirect:/gouwuche";
            modelAndView.addObject("message",baseResult.getMessage());
        }
        modelAndView.setViewName(redirect);
        return modelAndView;
    }

    @RequestMapping(value = "/list.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult list(ShopOrder shopOrder, HttpServletRequest request) {
        log.info("shopOrder  list ==> " + shopOrder);
        ShopUser shopUser = (ShopUser)request.getSession().getAttribute("user");
        shopOrder.setTokenId(shopUser.getTokenId());
        return orderService.orderList(shopOrder);
    }

}
