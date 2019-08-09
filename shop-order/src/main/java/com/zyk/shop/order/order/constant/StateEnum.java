package com.zyk.shop.order.order.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum StateEnum {
    deliveryNot(0,"未发货"),delivery(1,"已发货");
    @EnumValue
    private Integer state;
    private String desc;
    StateEnum(Integer state,String desc){
        this.state=state;
        this.desc=desc;
    }

}
