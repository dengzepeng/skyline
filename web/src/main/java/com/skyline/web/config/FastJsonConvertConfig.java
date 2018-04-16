package com.skyline.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

/**
 * fastjson配置
 *
 * @author skyline
 * @date 2018/1/19 9:13
 */
public class FastJsonConvertConfig {
    /**
     * 使用fastjson进行spring mvc json序列化
     *
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //创建包装对象
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //创建配置文件
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        converter.setFastJsonConfig(config);
        return new HttpMessageConverters(converter);
    }
}
