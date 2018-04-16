package com.skyline.entity.po;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_user")
@Data
public class TUser implements Serializable {
    @Id
    @Column(name = "user_id")
    private long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}
