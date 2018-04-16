package com.skyline.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "t_activity")
public class Activity implements Serializable {
    /**
     * 活动id
     */
    @Id
    @Column(name = "id", length = 18, columnDefinition = "BIGINT COMMENT 'id'")
    private long id;
    /**
     * 活动标题
     */
    @Column(name = "title")
    private String title;
    /**
     * 活动开始时间
     */
    @Column(name = "start_date", nullable = false, columnDefinition = "DATE COMMENT '活动开始时间'")
    private LocalDate startDate;
    /**
     * 活动结束时间
     */
    @Column(name = "end_date", nullable = false, columnDefinition = "DATE COMMENT '活动结束时间'")
    private LocalDate entDate;
    /**
     * 活动状态 0：未激活 1：进行中 2：已结束 3：已取消
     */
    @Column(name = "status",columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '活动状态 0：未激活 1：进行中 2：已结束 3：已取消'")
    private int status;

    @Column(name = "create_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;
    @Column(name = "update_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updateTime;
}
