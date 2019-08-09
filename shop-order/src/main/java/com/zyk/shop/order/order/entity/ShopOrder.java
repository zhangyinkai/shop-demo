package com.zyk.shop.order.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zyk.shop.order.order.constant.StateEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author zhangyk
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShopOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单Id,主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private String createTimeStr;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 发货状态,0=未发货,1=已发货
     */
    private StateEnum states;

    @TableField(exist = false)
    private String statesDesc;


    public String getStatesDesc() {
        if(states!=null){
            return states.getDesc();
        }
        return statesDesc;
    }

    public String getCreateTimeStr() {
        if(createTime!=null){
            return new SimpleDateFormat("yyyy/MM/dd HH:mm").format(createTime);
        }
        return createTimeStr;
    }
}
