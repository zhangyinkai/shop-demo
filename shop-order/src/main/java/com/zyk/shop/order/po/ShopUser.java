package com.zyk.shop.order.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShopUser implements Serializable {
    @JsonIgnore
    private static final long serialVersionUID=1L;
    /**
     * 用户Id,主键
     */

    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户名
     */
    private String userAlias;

    /**
     * 密码
     */
    @JsonIgnore
    private String loginKey;


    /**
     * 手机号码
     */
    private String phone;

    /**
     * 状态,0=正常
     */
    private Integer states;

    private String tokenId;
}
