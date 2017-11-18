package com.skyline.common.log;


import com.skyline.common.utils.CommUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * 公用日志工具类
 */
public class CommonLog {

    private static Logger accessLog = LoggerFactory.getLogger("accessLog");
    private static Logger bizLog = LoggerFactory.getLogger("bizLog");
    private static Logger infoLog = LoggerFactory.getLogger("infoLog");
    private static Logger errorLog = LoggerFactory.getLogger("errorLog");
    private static Logger behaviorLog = LoggerFactory.getLogger("behaviorLog");

    private static String addTid(String message) {
        return "t_id=" + CommUtil.getTid() + "," + message;
    }

    public static void access(String message) {
        access(LogLevel.DEBUG, message);// 默认debug级别
    }

    private static void access(LogLevel logLevel, String message) {
        message = addTid(message);
        if (logLevel == null) {
            accessLog.info(message);
        }
        if (logLevel == LogLevel.DEBUG) {
            accessLog.debug(message);
        } else if (logLevel == LogLevel.INFO) {
            accessLog.info(message);
        } else if (logLevel == LogLevel.ERROR) {
            accessLog.error(message);
        } else {
            accessLog.info(message);
        }
    }

    public static void biz(String message) {
        biz(LogLevel.INFO, message);
    }

    public static void biz(LogLevel logLevel, String message) {
        message = addTid(message);
        if (logLevel == null) {
            bizLog.info(message);
        }
        if (logLevel == LogLevel.DEBUG) {
            bizLog.debug(message);
        } else if (logLevel == LogLevel.INFO) {
            bizLog.info(message);
        } else if (logLevel == LogLevel.ERROR) {
            bizLog.error(message);
        } else {
            bizLog.info(message);
        }
    }

    public static void info(String message) {
        info(LogLevel.INFO, message);
    }

    public static void info(LogLevel logLevel, String message) {
        message = addTid(message);
        if (logLevel == null) {
            infoLog.info(message);
        }
        if (logLevel == LogLevel.DEBUG) {
            infoLog.debug(message);
        } else if (logLevel == LogLevel.INFO) {
            infoLog.info(message);
        } else if (logLevel == LogLevel.ERROR) {
            infoLog.error(message);
        } else {
            infoLog.info(message);
        }
    }

    public static void error(String message) {
        message = addTid(message);
        errorLog.error(message);
    }

    /**
     * 行为记录
     * @param request
     * @param behavior 行为类型BehaviorType:enum
     * @param tradMsg 根据行为类型所记录的业务数据
     * @param resultCode 处理结果返回码
     */
    public static void behavior(HttpServletRequest request, BehaviorType behavior, String tradMsg, String resultCode, String mobile, String userId){
        //日志输出格式为： 时间  | ip | appname(app的版本号) | DVID | DVUA | NT(网络类型) | ChannelId(渠道) | 客户端门户编号 | 行为类型 | 业务数据 | 返回结果 | 手机号码 | userId
        StringBuffer message = new StringBuffer(CommUtil.getIpAddr(request)).append("|");
        if (request != null) {
            message.append(request.getHeader("appname") == null ? (request.getParameter("appname") == null ? "" : request.getParameter("appname")) : request.getHeader("appname")).append("|");
            message.append(request.getHeader("DVID") == null ? (request.getParameter("DVID") == null ? "" : request.getParameter("DVID")) : request.getHeader("DVID")).append("|");
            message.append(request.getHeader("DVUA") == null ? (request.getParameter("DVUA") == null ? "" : request.getParameter("DVUA")) : request.getHeader("DVUA")).append("|");
            message.append(request.getHeader("NT") == null ? (request.getParameter("NT") == null ? "" : request.getParameter("NT")) : request.getHeader("NT")).append("|");
            message.append(request.getHeader("ChannelId") == null ? (request.getParameter("ChannelId") == null ? "" : request.getParameter("ChannelId")) : request.getHeader("ChannelId")).append("|");
        }
        message.append("").append("|");
        BehaviorType[] bt = BehaviorType.values();
        for(BehaviorType _bt : bt){
            if(_bt.name().equals(behavior.name())){
                message.append(_bt.getValue()).append("|");
            }

        }
        if(StringUtils.isEmpty(tradMsg)){
            message.append(" ");
        }else{
            message.append(tradMsg);
        }
        message.append("|");

        message.append(resultCode);

        message.append("|");
        message.append(mobile);
        message.append("|");
        message.append(userId);

        behaviorLog.info(message.toString());
    }

    public static void error(String message, Throwable t) {
        message = addTid(message);
        errorLog.error(message, t);
    }

}
