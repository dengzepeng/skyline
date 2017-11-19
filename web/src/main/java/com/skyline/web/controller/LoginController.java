package com.skyline.web.controller;

import com.skyline.common.page.ResultInfo;
import com.skyline.entity.po.TUser;
import com.skyline.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author skyline
 * @date 2017.11.19
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public ResultInfo login(@RequestBody TUser user){
        //TODO 参数校验

        return loginService.login(user.getUsername(),user.getPassword());
    }

}
