package com.skyline.entity.po;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息
 *
 * @author skyline
 * @date 2018/4/16 16:05
 */
public class Message implements Serializable {
    /**
     * 消息id
     */
    private long id;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 内容
     */
    private String content;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
