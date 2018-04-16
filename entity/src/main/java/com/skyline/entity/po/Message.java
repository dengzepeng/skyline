package com.skyline.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息
 *
 * @author skyline
 * @date 2018/4/16 16:05
 */
@Data
@Entity
@Table(name = "t_message")
public class Message implements Serializable {
    /**
     * 消息id
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
     * 内容
     */
    @Column(name = "content",length = 1024,columnDefinition = "VARCHAR(1024) COMMENT '内容'")
    private String content;

    /**
     * 状态 0:未读 1：已读
     */
    @Column(name = "status",columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '状态 0:未读 1：已读'")
    private int status;

    @Column(name = "create_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;
    @Column(name = "update_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updateTime;
}
