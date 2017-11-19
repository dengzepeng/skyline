package com.skyline.web.controller;


import com.skyline.common.log.CommonLog;
import com.skyline.common.page.ResultInfo;
import com.skyline.entity.po.TUser;
import com.skyline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author skyline
 * @date 2017.11.18
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("findAll")
    public ResultInfo findAll(){
        CommonLog.info("hello dzp");
        List<TUser> list = userService.findAll();
        return ResultInfo.success(list);
    }

    @PostMapping("register")
    public ResultInfo register(@RequestBody TUser user){
        return userService.register(user);
    }

    static TUser addUser(ScriptEngine engine) throws ScriptException, IOException, NoSuchMethodException {

        URL url = new URL("http://orpz1adxx.bkt.clouddn.com/addUser.js");
        InputStream is = new BufferedInputStream(url.openStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        StringBuilder stringBuilder = new StringBuilder();
        String str = null;
        while ((str = reader.readLine()) != null){
            stringBuilder.append(str);
        }

        reader.close();
        is.close();

        engine.eval(stringBuilder.toString());
        Invocable invocable = (Invocable) engine;
        TUser user = new TUser();
        user.setUserId("123");
        user.setUsername("邓泽鹏");
        user.setPassword("123456");
        return (TUser) invocable.invokeFunction("addUser",user);
    }

    //http://orpz1adxx.bkt.clouddn.com/addUser.js

    /**
     * 使用动态脚本修改java对象Demo
     * @param args
     * @throws ScriptException
     * @throws IOException
     * @throws NoSuchMethodException
     */
    public static void main(String[] args) throws ScriptException, IOException, NoSuchMethodException {
        TUser user = addUser(new ScriptEngineManager().getEngineByName("nashorn"));
        System.out.println(user.getUserId()+","+user.getUsername()+","+user.getPassword());
    }
}
