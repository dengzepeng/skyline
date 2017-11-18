package com.skyline.common.annotation;

import java.lang.annotation.*;


/**
 * 自定义拦截日志 controller 层
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
	String description()  default "" ;
}
