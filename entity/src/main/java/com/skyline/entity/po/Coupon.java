package com.skyline.entity.po;


import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券
 *
 * @author skyline
 * @date 2018/4/16 16:05
 */
@Data
public class Coupon implements Serializable {
    /**
     * 优惠券id
     */
    private long id;

    /**
     * 活动id
     */
    private long activityId;

}
