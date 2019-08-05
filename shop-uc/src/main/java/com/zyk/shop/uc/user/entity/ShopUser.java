package com.zyk.shop.uc.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zhangyk
 * @since 2019-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShopUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户Id,主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    private String loginKey;

    /**
     * 加盐字符串
     */
    private String salt;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 状态,0=正常
     */
    private Integer states;


}