package com.zyk.shop.portal.po;

import lombok.Getter;

@Getter
public enum StateEnum {
    deliveryNot(0,"未发货"),delivery(1,"已发货");
    private Integer state;
    private String desc;
    StateEnum(Integer state, String desc){
        this.state=state;
        this.desc=desc;
    }
}
