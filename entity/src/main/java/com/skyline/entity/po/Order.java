package com.skyline.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单
 *
 * @author skyline
 * @date 2018/4/16 16:04
 */
@Data
public class Order implements Serializable {
    /**
     * 订单id
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
     * 订单状态 0：取消 1：创建 2：已完成
     */
    private int status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
