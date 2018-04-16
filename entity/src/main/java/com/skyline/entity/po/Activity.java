package com.skyline.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 活动
 *
 * @author skyline
 * @date 2018/4/16 17:23
 */
@Data
public class Activity implements Serializable {
    /**
     * 活动id
     */
    private long id;
    /**
     * 活动标题
     */
    private String title;
    /**
     * 活动开始时间
     */
    private LocalDate startDate;
    /**
     * 活动结束时间
     */
    private LocalDate entDate;
    /**
     * 活动状态 0：未激活 1：进行中 2：已结束 3：已取消
     */
    private int status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
