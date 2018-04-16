package com.skyline.entity.po;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 意见和建议
 *
 * @author skyline
 * @date 2018/4/16 16:06
 */
public class Opinion implements Serializable {
    /**
     * id
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
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
