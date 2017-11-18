package com.skyline.web.controller;


import com.skyline.common.log.CommonLog;
import com.skyline.common.page.ResultInfo;
import com.skyline.entity.po.TUser;
import com.skyline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("findAll")
    public ResultInfo findAll(){
        CommonLog.info("hello dzp");
        List<TUser> list = userService.findAll();
        return ResultInfo.success(list);
    }
}
