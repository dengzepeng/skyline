package com.skyline.common.logs;

import com.skyline.common.ThreadLocalManager;
import com.skyline.common.utils.CommUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 日志
 *
 * @author skyline
 */
public enum Loggers {
    /**
     * 非致命性异常根
     */
    TOTAL(LogManager.getLogger("total"));


    Loggers(Logger logger) {
        this.logger = logger;
    }

    private Logger logger;

    public void debug(String message, Object... args) {
        String content = concatMessage(message, args);
        if (!StringUtils.isEmpty(content)) {
            logger.debug(content);
        }
    }

    public void info(String message, Object... args) {
        String content = concatMessage(message, args);
        if (!StringUtils.isEmpty(content)) {
            logger.info(content);
        }
    }

    public void error(String message, Throwable t) {
        String content = concatMessage(message);
        if (!StringUtils.isEmpty(content)) {
            logger.error(message, t);
        }
    }

    public void error(String message, Throwable t, Object... args) {
        String content = concatMessage(message, args);
        if (!StringUtils.isEmpty(content)) {
            logger.error(content, t);
        }
    }

    public void error(String message, Object... args) {
        String content = concatMessage(message, args);
        if (!StringUtils.isEmpty(content)) {
            logger.error(content);
        }
    }

    public void trace(String message, Object... args) {
        String content = concatMessage(message, args);
        if (!StringUtils.isEmpty(content)) {
            logger.trace(content);
        }
    }

    public void warn(String message, Object... args) {
        String content = concatMessage(message, args);
        if (!StringUtils.isEmpty(content)) {
            logger.warn(content);
        }
    }


    private String concatMessage(String message, Object... args) {
        if (StringUtils.isEmpty(message)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String tid = ThreadLocalManager.getTid();
        if (null != tid) {
            sb.append(ThreadLocalManager.getTid()).append(", ");
        }
        if (args.length == 0) {
            sb.append(message);
        } else {
            int k = 0;
            int index = 0;
            String findText = "{}";
            while ((index = message.indexOf(findText, index)) != -1) {
                index = index + findText.length();
                k++;
            }
            if (k > args.length) {
                return "";
            }
            for (int j = 0; j < k; j++) {
                message = StringUtils.replaceOnce(message, "{}", CommUtil.null2String(args[j]));
            }
            sb.append(message);
        }
        return sb.toString();
    }
}
