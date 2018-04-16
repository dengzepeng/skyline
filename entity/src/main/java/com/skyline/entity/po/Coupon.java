package com.skyline.entity.po;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 优惠券
 *
 * @author skyline
 * @date 2018/4/16 16:05
 */
@Data
@Entity
@Table(name = "t_coupon")
public class Coupon implements Serializable {
    /**
     * 优惠券id
     */
    @Id
    @Column(name = "id", length = 18, columnDefinition = "BIGINT COMMENT 'id'")
    private long id;

    /**
     * 活动id
     */
    @Column(name = "activity_id", length = 18, columnDefinition = "BIGINT COMMENT '活动id'")
    private long activityId;

    /**
     * 优惠券发放数量
     */
    @Column(name = "coupon_count", columnDefinition = "BIGINT COMMENT '优惠券发放数量'")
    private long couponCount;

    /**
     * 优惠券领取数量
     */
    @Column(name = "get_count", columnDefinition = "BIGINT COMMENT '优惠券领取数量'")
    private long getCount;

    /**
     * 优惠券已使用数量
     */
    @Column(name = "used_count", columnDefinition = "BIGINT COMMENT '优惠券发放数量'")
    private long usedCount;

    /**
     * 金额
     */
    @Column(name = "amount",columnDefinition = "DECIMAL COMMENT '金额'")
    private BigDecimal amount;

    /**
     * 总金额
     */
    @Column(name = "total_amount",columnDefinition = "DECIMAL COMMENT '总金额'")
    private BigDecimal totalAmount;
}
