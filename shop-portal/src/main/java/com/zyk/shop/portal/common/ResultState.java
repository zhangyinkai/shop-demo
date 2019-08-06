package com.zyk.shop.portal.common;

public enum ResultState {
    success(0),error(1);
    private int code;
    ResultState(int code){
        this.code=code;
    }
    public int getCode() {
        return code;
    }
}
