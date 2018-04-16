package com.skyline.entity.po;

import javax.persistence.Column;
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
@Table(name = "t_goods")
public class Goods implements Serializable {
    /**
     * 商品id
     */
    @Column(name = "id", length = 18, columnDefinition = "BIGINT COMMENT 'id'")
    private long id;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 商品描述
     */
    private String desc;


    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
