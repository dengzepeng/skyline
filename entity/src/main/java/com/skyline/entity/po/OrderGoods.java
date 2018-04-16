package com.skyline.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单商品关系
 *
 * @author skyline
 * @date 2018/4/16 16:28
 */
@Data
@Entity
@Table(name = "t_order_goods")
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
    @Column(name = "order_id", length = 18, columnDefinition = "BIGINT COMMENT '订单id'")
    private long orderId;
    /**
     * 商品id
     */
    @Column(name = "goods_id", length = 18, columnDefinition = "BIGINT COMMENT '商品id'")
    private long goodsId;


    @Column(name = "create_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;
    @Column(name = "update_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updateTime;
}
