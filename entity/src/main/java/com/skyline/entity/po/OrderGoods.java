package com.skyline.entity.po;

import java.io.Serializable;

/**
 * 订单商品关系
 *
 * @author skyline
 * @date 2018/4/16 16:28
 */
public class OrderGoods implements Serializable {
    /**
     * id
     */
    private long id;
    /**
     * 订单id
     */
    private long orderId;
    /**
     * 商品id
     */
    private long goodsId;
}
