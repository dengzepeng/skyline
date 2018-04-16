package com.skyline.web.controller;


import com.skyline.common.logs.Loggers;
import com.skyline.common.page.ResultInfo;
import com.skyline.entity.po.User;
import com.skyline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author skyline
 * @date 2017.11.18
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("user")
    public ResultInfo findAll(){
        List<User> list = userService.findAll();
        Loggers.TOTAL.info("dzp");
        return ResultInfo.success(list);
    }

    @PostMapping("user")
    public ResultInfo register(@RequestBody User user){
        return userService.register(user);
    }

}
