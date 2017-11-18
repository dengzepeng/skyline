package com.skyline.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsTest {
    public static void main(String args[]) throws IOException, ScriptException, NoSuchMethodException {

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        Resource resource = new ClassPathResource("/scripts/test.js");
        InputStream inputStream = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder stringBuilder = new StringBuilder();
        String str = null;
        while ((str = reader.readLine()) != null){
            stringBuilder.append(str);
        }


        reader.close();
        inputStream.close();

        System.out.println(stringBuilder);
        engine.eval(stringBuilder.toString());

        Invocable invocable = (Invocable) engine;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("姓名","邓泽鹏");
        jsonObject.put("年龄","26");
        jsonObject.put("性别","男");
        invocable.invokeFunction("fun",jsonObject);
    }
}