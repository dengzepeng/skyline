package com.skyline.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author skyline
 * @date 2018/4/16 14:32
 */
@Data
@Entity
@Table(name = "t_user_info")
public class UserInfo implements Serializable {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id", length = 18, columnDefinition = "BIGINT COMMENT '用户id'")
    private long userId;

    /**
     * 姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255) COMMENT '姓名'")
    private String name;

    /**
     * 邮箱
     */
    @Column(name = "email", columnDefinition = "VARCHAR(255) COMMENT '邮箱'")
    private String email;

    /**
     * 积分
     */
    @Column(name = "bonus_points", columnDefinition = "BIGINT DEFAULT 0 COMMENT '消费积分'")
    private long bonusPoints;

    @Column(name = "update_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updateTime;
}
