package com.skyline.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 订单商品关系
 *
 * @author skyline
 * @date 2018/4/16 16:28
 */
@Data
@Table(name = "t_order")
public class OrderGoods implements Serializable {
    /**
     * id
     */
    @Id
    @Column(name = "id", length = 18, columnDefinition = "BIGINT COMMENT 'id'")
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
