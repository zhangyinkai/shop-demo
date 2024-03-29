package com.zyk.shop.portal.po;

import com.zyk.shop.portal.common.BaseResult;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ShopUserResult extends BaseResult {
    private ShopUser body;
}
