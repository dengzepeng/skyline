package com.skyline.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Entity
@Table(name = "t_shipping_address")
public class ShippingAddress implements Serializable {
    /**
     * id
     */
    @Id
    @Column(name = "id", length = 18, columnDefinition = "BIGINT COMMENT 'id'")
    private long id;

    /**
     * 用户id
     */
    @Column(name = "user_id", length = 18, columnDefinition = "BIGINT COMMENT '用户id'")
    private long userId;
    /**
     * 省
     */
    @Column(name = "province",length = 128,columnDefinition = "VARCHAR(128) COMMENT '省'")
    private String province;
    /**
     * 市
     */
    @Column(name = "city",length = 128,columnDefinition = "VARCHAR(128) COMMENT '市'")
    private String city;
    /**
     * 区
     */
    @Column(name = "district",length = 128,columnDefinition = "VARCHAR(128) COMMENT '区'")
    private String district;
    /**
     * 地址
     */
    @Column(name = "address",length = 128,columnDefinition = "VARCHAR(128) COMMENT '地址'")
    private String address;

    @Column(name = "create_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;
    @Column(name = "update_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updateTime;
}
