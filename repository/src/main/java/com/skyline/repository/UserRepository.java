package com.skyline.repository;

import com.skyline.entity.po.TUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TUser,String> {
    TUser findByUsername(String username);
}
