package com.skyline.entity.po;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 优惠券
 *
 * @author skyline
 * @date 2018/4/16 16:05
 */
@Data
@Table(name = "t_coupon")
public class Coupon implements Serializable {
    /**
     * 优惠券id
     */
    @Column(name = "id", length = 18, columnDefinition = "BIGINT COMMENT 'id'")
    private long id;

    /**
     * 活动id
     */
    private long activityId;

}
