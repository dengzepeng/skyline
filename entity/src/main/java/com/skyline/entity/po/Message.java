package com.skyline.entity.po;

import lombok.Data;

import javax.persistence.Column;
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
@Table(name = "t_message")
public class Message implements Serializable {
    /**
     * 消息id
     */
    @Column(name = "id", length = 18, columnDefinition = "BIGINT COMMENT 'id'")
    private long id;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态 0:未读 1：已读
     */
    private int status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
