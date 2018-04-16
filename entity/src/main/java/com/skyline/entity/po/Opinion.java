package com.skyline.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 意见和建议
 *
 * @author skyline
 * @date 2018/4/16 16:06
 */
@Data
@Entity
@Table(name = "t_opinion")
public class Opinion implements Serializable {
    /**
     * id
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

    @Column(name = "create_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;
}
