package com.skyline.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品
 *
 * @author skyline
 * @date 2018/4/16 18:10
 */
public class Goods implements Serializable {
    /**
     * 商品id
     */
    private long id;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品价格
     */
    private BigDecimal price;


    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
