package com.skyline.entity.po;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "t_user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id",length = 18,columnDefinition = "BIGINT(18) COMMENT '用户id'")
    private long userId;

    /**
     * 用户名
     */
    @Column(name = "username",columnDefinition = "VARCHAR(255) COMMENT '用户名'")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password",columnDefinition = "VARCHAR(255) COMMENT '密码'")
    private String password;

    /**
     * 手机号
     */
    @Column(name = "mobile",length = 11,columnDefinition = "VARCHAR(11) COMMENT '手机号'")
    private String mobile;

    /**
     * 头像
     */
    @Column(name = "avatar",columnDefinition = "VARCHAR(255) COMMENT '头像'")
    private String avatar;

    @Column(name = "create_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDate createTime;
    @Column(name = "update_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDate updateTime;
}
