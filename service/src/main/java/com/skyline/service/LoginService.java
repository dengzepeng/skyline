package com.skyline.service;

import com.skyline.common.page.ResultInfo;

/**
 * @author skyline
 * @date 2017.11.19
 */
public interface LoginService {
    public ResultInfo login(String username, String password);
}
