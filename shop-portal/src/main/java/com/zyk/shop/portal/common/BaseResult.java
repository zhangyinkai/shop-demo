package com.zyk.shop.portal.common;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseResult {
    private int code = ResultState.success.getCode();
    private String message = ResultState.success.toString();
    private Object body;
}
