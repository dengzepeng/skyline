package com.skyline.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品
 *
 * @author skyline
 * @date 2018/4/16 18:10
 */
@Entity
@Data
@Table(name = "t_goods")
public class Goods implements Serializable {
    /**
     * 商品id
     */
    @Id
    @Column(name = "id", length = 18, columnDefinition = "BIGINT COMMENT 'id'")
    private long id;
    /**
     * 商品标题
     */
    @Column(name = "title",columnDefinition = "VARCHAR(255) COMMENT '商品标题'")
    private String title;
    /**
     * 商品价格
     */
    @Column(name = "price",columnDefinition = "DECIMAL COMMENT '商品价格'")
    private BigDecimal price;
    /**
     * 商品描述
     */
    @Column(name = "description",columnDefinition = "VARCHAR(1024) COMMENT '商品描述'")
    private String description;


    @Column(name = "create_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;
    @Column(name = "update_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updateTime;
}
