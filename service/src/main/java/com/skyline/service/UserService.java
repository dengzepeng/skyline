package com.skyline.service;

import com.skyline.common.page.ResultInfo;
import com.skyline.entity.po.User;

import java.util.List;

/**
 * @author skyline
 * @date 2017.11.19
 */
public interface UserService {
    List<User> findAll();

    User findByUsername(String username);

    ResultInfo register(User user);
}
