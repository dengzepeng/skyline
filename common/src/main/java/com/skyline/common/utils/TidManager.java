package com.skyline.common.utils;

/**
 * 保证同一个线程的t_id是相同的
 * t_id用来标识一个请求流程
 */
public class TidManager {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static String getTid() {
        return threadLocal.get();
    }

    public static void setTid(String tid) {
        threadLocal.set(tid);
    }

    public static void clear() {
        threadLocal.remove();
    }

}
