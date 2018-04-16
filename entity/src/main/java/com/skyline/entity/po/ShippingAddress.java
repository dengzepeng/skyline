package com.skyline.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 配送地址
 *
 * @author skyline
 * @date 2018/4/16 17:58
 */
@Data
public class ShippingAddress implements Serializable {
    /**
     * id
     */
    private long id;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 地址
     */
    private String address;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
