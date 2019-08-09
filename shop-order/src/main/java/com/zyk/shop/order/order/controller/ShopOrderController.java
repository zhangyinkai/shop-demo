package com.zyk.shop.order.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyk.shop.order.common.BaseResult;
import com.zyk.shop.order.common.OrderUtil;
import com.zyk.shop.order.common.ResultState;
import com.zyk.shop.order.order.constant.StateEnum;
import com.zyk.shop.order.order.entity.ShopOrder;
import com.zyk.shop.order.order.service.IShopOrderService;
import com.zyk.shop.order.po.ShopUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.AssertTrue;
import java.util.Date;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author zhangyk
 * @since 2019-08-08
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class ShopOrderController {

    @Autowired
    private IShopOrderService iShopOrderService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult list(ShopOrder shopOrder,@RequestAttribute("shopUser") ShopUser shopUser){
        log.info("shopOrder  ==> "+shopOrder);
        log.info("shopUser  ==> "+shopUser);
        Assert.notNull(shopUser.getId(), "user id is required");
        QueryWrapper<ShopOrder> ew = new QueryWrapper();
        if(shopOrder==null) shopOrder = new ShopOrder();
        shopOrder.setUserId(shopUser.getId());
        ew.setEntity(shopOrder);
        BaseResult baseResult = new BaseResult();
        baseResult.setBody(iShopOrderService.list(ew));
        log.info("<== "+baseResult);
        return baseResult;
    }

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult order(@RequestBody ShopOrder shopOrder,@RequestAttribute("shopUser") ShopUser shopUser){
        log.info("shopOrder  ==> "+shopOrder);
        log.info("shopUser  ==> "+shopUser);
        BaseResult baseResult = new BaseResult();
        shopOrder.setCreateTime(new Date());
        shopOrder.setOrderNo(OrderUtil.getOrderNo(shopUser.getId()));
        shopOrder.setUserId(shopUser.getId());
        shopOrder.setStates(StateEnum.deliveryNot);
        if(!iShopOrderService.save(shopOrder)){
            baseResult.setCode(ResultState.error.getCode());
            baseResult.setMessage("添加订单错误~");
        };
        log.info("<== "+baseResult);
        return baseResult;
    }

}

