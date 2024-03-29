package com.zyk.shop.uc.common;
import lombok.Data;

@Data
public class BaseResult {
    private int code = ResultState.success.getCode();
    private String message = ResultState.success.toString();
    private Object body;
}
