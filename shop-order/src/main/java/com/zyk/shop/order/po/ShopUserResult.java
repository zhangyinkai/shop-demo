package com.zyk.shop.order.po;

import com.zyk.shop.order.common.BaseResult;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ShopUserResult extends BaseResult {
    private ShopUser body;
}
