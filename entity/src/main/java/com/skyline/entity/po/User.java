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
    @Id
    @Column(name = "user_id",length = 18)
    private long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "mobile",length = 11)
    private String mobile;
    @Column(name = "create_time")
    private LocalDate createTime;
}
