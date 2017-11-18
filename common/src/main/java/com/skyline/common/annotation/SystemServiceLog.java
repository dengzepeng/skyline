package com.skyline.common.annotation;

import java.lang.annotation.*;


/**
 * 
 * 自定义拦截日志 service层
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {

	 String description()  default "";
}
