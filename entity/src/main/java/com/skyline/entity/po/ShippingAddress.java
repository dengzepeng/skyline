package com.skyline.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 配送地址
 *
 * @author skyline
 * @date 2018/4/16 17:58
 */
@Data
@Table(name = "t_shipping_address")
public class ShippingAddress implements Serializable {
    /**
     * id
     */
    @Column(name = "id", length = 18, columnDefinition = "BIGINT COMMENT 'id'")
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
