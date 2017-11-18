package com.skyline.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.URL;

public class JsTest {
    public static void main(String args[]) throws IOException, ScriptException, NoSuchMethodException {

        /*ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
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

        Invocable invocable = (Invocable) engine;*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("姓名","邓泽鹏");
        jsonObject.put("年龄","26");
        jsonObject.put("性别","男");
        //invocable.invokeFunction("fun",jsonObject);





        //http://orpz1adxx.bkt.clouddn.com/test.js
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        Invocable invocable = (Invocable) engine;
        URL url = new URL("http://orpz1adxx.bkt.clouddn.com/test.js");
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
        invocable.invokeFunction("fun",jsonObject);
    }
}