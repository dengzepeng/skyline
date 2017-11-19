package com.skyline.web.config;

import com.skyline.common.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 描述：记录访问设备信息
 * @author pengyao
 * 创建时间 2017/8/25.
 */
@Configuration
public class ServletContextConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/**");
    }
}
