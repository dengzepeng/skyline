package com.skyline.common;

/**
 * 保证同一个线程的t_id是相同的
 * t_id用来标识一个请求流程
 */
public class ThreadLocalManager {

    private static ThreadLocal<String> tidLocal = new ThreadLocal<>();
    private static ThreadLocal<String> ipLocal = new ThreadLocal<>();

    public static String getTid() {
        return tidLocal.get();
    }

    public static void setTid(String tid) {
        tidLocal.set(tid);
    }

    public static void clear() {
        tidLocal.remove();
        ipLocal.remove();
    }

    public static String getIpLocal() {
        return ipLocal.get();
    }

    public static void setIpLocal(String ip) {
        ipLocal.set(ip);
    }

}
