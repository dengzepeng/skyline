package com.skyline.service;

import com.skyline.common.page.ResultInfo;
import com.skyline.entity.po.TUser;

import java.util.List;

/**
 * @author skyline
 * @date 2017.11.19
 */
public interface UserService {
    List<TUser> findAll();

    TUser findByUsername(String username);

    ResultInfo register(TUser user);
}
