package com.skyline.common.annotation;

import java.lang.annotation.*;


/**
 * 自定义拦截日志 controller 层
 * 仅针对controller方法以@requestBody取参使用，用来获取入参日志
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerAroundLog {
	String description()  default "" ;
}
