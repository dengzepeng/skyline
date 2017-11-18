package com.skyline.common.annotation;

import com.alibaba.fastjson.JSON;
import com.skyline.common.log.CommonLog;
import com.skyline.common.utils.CommUtil;
import com.skyline.common.utils.TidManager;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@Aspect
@Component
public class SystemLogAspect {

    //Service层切点
    @Pointcut("@annotation(com.skyline.common.annotation.SystemServiceLog)")
    public void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(com.skyline.common.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }

    @Pointcut("@annotation(com.skyline.common.annotation.SystemControllerAroundLog)")
    public void controllerAround() {
    }

    /**
     * controller层 return Advice结果日志打印
     *
     * @param joinPoint
     * @param retVal
     */
    @AfterReturning(value = "controllerAspect()", returning = "retVal")
    public void doControllerAfter(JoinPoint joinPoint, Object retVal) {

        try {
            if (retVal instanceof String) {
                CommonLog.access(getControllerMethodDescription(joinPoint) + " result of returns for client: " + retVal);
            } else {
                CommonLog.access(getControllerMethodDescription(joinPoint) + " result of returns for client: " + JSON.toJSONString(retVal));
            }
        } catch (Exception e) {
            CommonLog.error("com.by56.common.annotation.SystemLogAspect.affterControllerReturning 异常", e);
        }

    }

    /**
     * service return Advice结果日志打印
     *
     * @param joinPoint
     */
    @AfterReturning(value = "serviceAspect()", returning = "retVal")
    public void doServiceAfter(JoinPoint joinPoint, Object retVal) {
        try {
            String retStr = getServiceMthodDescription(joinPoint);
            if (retVal instanceof String) {
                CommonLog.access(retStr + " result of returns for client: " + retVal);
            } else {
                CommonLog.access(retStr + " result of returns for client: " + JSON.toJSONString(retVal));
            }
        } catch (Exception e) {
            CommonLog.error("com.by56.common.annotation.SystemLogAspect.affterServiceReturning 异常", e);
        } finally {
//			TidManager.clear();
        }

    }

    @Around(value = "controllerAround()")
    public Object doControllerAround(ProceedingJoinPoint pjp) throws Throwable {
        String argsLog = getControllerMethodArgsDescription(pjp);
        CommonLog.access(argsLog);
        Object[] args = pjp.getArgs();
        return pjp.proceed(args);
    }


    private static String getControllerMethodArgsDescription(ProceedingJoinPoint pjp) throws ClassNotFoundException {
        String targetName = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] arguments = pjp.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
                    String[] args = discoverer.getParameterNames(method);
                    //入参
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        String key = args[i];
                        String valueStr = "";
                        Object value = arguments[i];
                        if (CommUtil.isNotNull(value)) {
                            if (CommUtil.isBasicDataType(value) || value instanceof BindingResult || value instanceof HttpServletResponse || value instanceof HttpServletRequest) {
                                valueStr = value.toString();
                            } else {
                                valueStr = JSON.toJSONString(value);
                            }
                        }
                        sb.append(key).append("=").append(valueStr).append("&");
                    }
                    String retArgs = sb.toString();
                    if (StringUtils.isNotBlank(retArgs) && retArgs.endsWith("&")) {
                        retArgs = retArgs.substring(0, retArgs.length() - 1);
                    }
                    description = targetName + "." + methodName + "?" + retArgs;
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
                    String[] args = discoverer.getParameterNames(method);
                    //入参
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        if ("t_id".equals(args[i]) || "tid".equals(args[i]) || "Tid".equals(args[i])) {
                            TidManager.setTid(arguments[i].toString());
                        }
                        sb.append(args[i]).append("=").append(arguments[i].toString()).append("&");
                    }
                    CommonLog.access("acc Service 请求参数  :" + sb.toString());
                    description = targetName + "." + methodName + ": " + method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = targetName + "." + methodName + ": " + method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
