package com.skyline.common.utils;

import com.skyline.common.ThreadLocalManager;
import com.skyline.common.logs.Loggers;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

public class CommUtil {

    public static String separator = new String(new byte[]{0x1});

    /**
     * 时间 字符串格式化（yyyy-MM-dd HH:mm:ss）
     *
     * @param s
     * @return
     */
    public static Date commDateParse(String s) {
        Date d = null;
        try {
            d = dateParse(s, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception localException) {
        }
        return d;
    }

    /**
     * 格式化时间字符串为Date
     *
     * @param str
     * @param format
     * @return
     */
    public static Date dateParse(String str, String format) {
        LocalDateTime date = localDateTimeParse(str, format);
        if (null == date) {
            return null;
        }
        Instant instant = date.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * 时间字符串解析为LocalDateTime
     * <p><b>note:</b>格式模板长度和时间字符串长度一致</p>
     *
     * @param s
     * @param format
     * @return
     */
    public static LocalDateTime localDateTimeParse(String s, String format) {
        if (StringUtils.isBlank(s) || StringUtils.isBlank(format)) {
            return null;
        }
        LocalDateTime d = null;
        try {
            d = LocalDateTime.parse(s, DateTimeFormatter.ofPattern(format));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return d;
    }

    /**
     * 时间字符串解析为LocalDateTime
     * <p><b>note:</b>格式模板长度和时间字符串长度一致</p>
     *
     * @param s
     * @param format
     * @return
     */
    public static LocalDate localDateParse(String s, String format) {
        if (StringUtils.isBlank(s) || StringUtils.isBlank(format)) {
            return null;
        }
        LocalDate d = null;
        try {
            d = LocalDate.parse(s, DateTimeFormatter.ofPattern(format));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return d;
    }


    /**
     * 时间字符串解析为LocalDateTime
     * <p><b>note:</b>格式模板长度和时间字符串长度一致</p>
     *
     * @param s
     * @param format
     * @return
     */
    public static LocalTime localTimeParse(String s, String format) {
        if (StringUtils.isBlank(s) || StringUtils.isBlank(format)) {
            return null;
        }
        LocalTime d = null;
        try {
            d = LocalTime.parse(s, DateTimeFormatter.ofPattern(format));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return d;
    }

    /**
     * LocalDateTime 格式化
     *
     * @param format
     * @param v
     * @return
     */
    public static String dateFormat(String format, LocalDateTime v) {
        if (v == null) {
            return null;
        }
        return v.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 格式化date时间
     *
     * @param format
     * @param date
     * @return
     */
    public static String formatLongDate(String format, Date date) {
        if ((date == null) || ("".equals(format))) {
            return "";
        }
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 毫秒转date
     *
     * @param second
     * @return
     */
    public static Date parseSeconds(long second) {
        Date date = new Date();
        date.setTime(second);
        return date;
    }

    public static boolean isNotNull(Object obj) {
        return (null != obj) && (!"".equals(obj.toString())) && (!"null".equals(obj.toString()));
    }


    public static int null2Int(Object s) {
        int v = 0;
        if (s != null) {
            try {
                v = Integer.parseInt(s.toString());
            } catch (Exception localException) {
            }
        }
        return v;
    }

    public static float null2Float(Object s) {
        float v = 0.0F;
        if (s != null) {
            try {
                v = Float.parseFloat(s.toString());
            } catch (Exception localException) {
            }
        }
        return v;
    }

    public static double null2Double(Object s) {
        double v = 0.0D;
        if (s != null) {

            try {
                v = Double.parseDouble(null2String(s));
            } catch (Exception localException) {
            }
        }

        return v;
    }

    public static boolean null2Boolean(Object s) {
        boolean v = false;
        if (s != null) {
            try {
                v = Boolean.parseBoolean(s.toString());
            } catch (Exception localException) {
            }
        }
        return v;
    }

    public static String null2String(Object s) {
        return null == s || "null".equals(s) ? "" : s.toString().trim();
    }

    public static Long null2Long(Object s) {
        Long v = -1L;
        if (s != null) {
            try {
                v = Long.parseLong(s.toString());
            } catch (Exception localException) {
            }
        }
        return v;
    }

    public static BigDecimal null2BigDecimal(Object s) {
        if (s != null) {
            try {
                if (s instanceof Double || s instanceof Float
                        || s instanceof Integer || s instanceof Long
                        || s instanceof String) {
                    return new BigDecimal(s.toString().trim());
                } else {
                    return BigDecimal.ZERO;
                }
            } catch (Exception localException) {
            }
        }
        return BigDecimal.ZERO;
    }

    //毫秒
    public static String getTimeInfo(long time) {
        int hour = (int) time / 3600000;
        long balance = time - hour * 1000 * 60 * 60;
        int minute = (int) balance / 60000;
        balance -= minute * 1000 * 60;
        int seconds = (int) balance / 1000;
        String ret = "";
        if (hour > 0) {
            ret = ret + hour + "小时";
        }
        if (minute > 0) {
            ret = ret + minute + "分";
        } else if ((minute <= 0) && (seconds > 0)) {
            ret = ret + "零";
        }
        if (seconds > 0) {
            ret = ret + seconds + "秒";
        }
        return ret;
    }

    //秒
    public static String sec2Date(long time) {
        int hour = (int) time / 3600;
        long balance = time - hour * 60 * 60;
        int minute = (int) balance / 60;
        balance -= minute * 60;
        int seconds = (int) balance;
        String ret = "";
        if (hour > 0) {
            ret = ret + hour + "小时";
        }
        if (minute > 0) {
            ret = ret + minute + "分";
        } else if ((minute <= 0) && (seconds > 0)) {
            ret = ret + "零";
        }
        if (seconds > 0) {
            ret = ret + seconds + "秒";
        }
        return ret;
    }

    public static String getIpAddr(HttpServletRequest request) {
        try {
            String ip = request.getHeader("x-forwarded-for");
            if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getRemoteAddr();
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("http_client_ip");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }

            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress addr = null;
                try {
                    addr = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    Loggers.TOTAL.error("", e);
                }
                ip = null2String(addr.getHostAddress());
            }
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  //"***.***.***.***".length() = 15
            if (ip != null && ip.length() > 15) {
                if (ip.indexOf(",") > 0) {
                    ip = ip.substring(0, ip.indexOf(","));
                }
            }
            return ip;
        } catch (Exception e) {
            return "";
        }
    }


    public static int parseDate(String type, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if ("y".equals(type)) {
            return cal.get(1);
        }
        if ("M".equals(type)) {
            return cal.get(2) + 1;
        }
        if ("d".equals(type)) {
            return cal.get(5);
        }
        if ("H".equals(type)) {
            return cal.get(11);
        }
        if ("m".equals(type)) {
            return cal.get(12);
        }
        if ("s".equals(type)) {
            return cal.get(13);
        }

        return 0;
    }

    public static String trimSpaces(String IP) {
        while (IP.startsWith(" ")) {
            IP = IP.substring(1, IP.length()).trim();
        }
        while (IP.endsWith(" ")) {
            IP = IP.substring(0, IP.length() - 1).trim();
        }
        return IP;
    }

    public static boolean isIp(String IP) {
        boolean b = false;
        IP = trimSpaces(IP);
        if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            String[] s = IP.split("\\.");
            if ((Integer.parseInt(s[0]) < 255)
                    && (Integer.parseInt(s[1]) < 255)
                    && (Integer.parseInt(s[2]) < 255)
                    && (Integer.parseInt(s[3]) < 255)) {
                b = true;
            }
        }
        return b;
    }

    public static String generic_domain(HttpServletRequest request) {
        String system_domain = "localhost";
        String serverName = request.getServerName();
        if (isIp(serverName)) {
            system_domain = serverName;
        } else if (serverName.indexOf(".") == serverName.lastIndexOf(".")) {
            system_domain = serverName;
        } else {
            system_domain = serverName.substring(serverName.indexOf(".") + 1);
        }
        return system_domain;
    }


    /**
     * 获取链接的后缀名
     *
     * @return
     */

    final static Pattern pattern = compile("\\S*[?]\\S*");

    public static String parseSuffix(String url) {

        Matcher matcher = pattern.matcher(url);

        String[] spUrl = url.toString().split("/");
        int len = spUrl.length;
        String endUrl = spUrl[len - 1];

        if (matcher.find()) {
            String[] spEndUrl = endUrl.split("\\?");
            String filename = spEndUrl[0];
            String[] nodes = filename.split("\\.");
            int length = nodes.length;
            return nodes[length - 1];
        } else {
            String filename = endUrl;
            String[] nodes = filename.split("\\.");
            int length = nodes.length;
            return nodes[length - 1];
        }
    }

    public static String getURL(HttpServletRequest request) {
        String contextPath = "/".equals(request.getContextPath()) ? "" : request.getContextPath();
        String url = request.getScheme() + "://" + request.getServerName();
        String port = String.valueOf(null2Int(Integer.valueOf(request.getServerPort())));
        if (null2Int(Integer.valueOf(request.getServerPort())) != 80) {
            url = url + ":" + port + contextPath;
        } else {
            url = url + contextPath;
        }
        return url;
    }

    private static String genTid() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS"));
//        new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
        //生成三位随机数
        char[] numbers = "0123456789".toCharArray();

        Random randGen = new Random();
        char[] randBuffer = new char[2];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbers[randGen.nextInt(9)];
        }
        return "119" + now + new String(randBuffer);
    }

    public static String getTid() {
        String t_Id = ThreadLocalManager.getTid();
        if (StringUtils.isNotEmpty(t_Id) && t_Id.startsWith("119")) {
            return t_Id;
        }
        t_Id = genTid();
        // 设置t_id
        ThreadLocalManager.setTid(t_Id);
        return t_Id;
    }

    /**
     * 获取生成token的UUID随机数，生成k值
     *
     * @return
     */
    public static String getGuid() {
        //创建guid对象，生成k值
        UUID uuid = UUID.randomUUID();

        //生成四位随机数
        char[] numbers = "0123456789".toCharArray();

        Random randGen = new Random();
        char[] randBuffer = new char[4];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbers[randGen.nextInt(9)];
        }

        String guid = uuid.toString().replaceAll("-", "") + new String(randBuffer);
        return guid;
    }

    /**
     * 验证手机号码格式是否正确
     *
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        Pattern pattern = compile("^1\\d{10}");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }


    /***
     * 生成指定位数的随机数
     * @param len
     * @return
     */
    public static String genRandomNum(int len) {
        //35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; //生成的随机数
        //生成的密码的长度
        int count = 0;
        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < len) {
            //生成随机数，取绝对值，防止生成负数，
            //生成的数最大为36-1
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }


    /**
     * 四舍五入
     *
     * @param num
     * @param scale
     * @return
     */
    public static double round(double num, int scale) {
        BigDecimal big = new BigDecimal(num);
        BigDecimal result = big.divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP);
        return result.doubleValue();
    }


    public static String convertHtmlToDegree(String str) {
        if (StringUtils.isNotBlank(str)) {
            return str.replace("&deg;", "°");
        }
        return str;
    }


    /**
     * 将空字符串设置返回""
     *
     * @return
     */
    public static String toSetEmptyValue(Object obj) {
        if (CommUtil.isNotNull(obj)) {
            return obj.toString();
        } else {
            return "";
        }
    }


    /**
     * 格式化一个数字为指定格式的bigdecimal
     *
     * @return
     */
    public static BigDecimal formatBigDecimal(BigDecimal number, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return new BigDecimal(df.format(number));
    }

    public static String formatBigDecimal2String(BigDecimal number, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(number);
    }

    /**
     * @param res 文件在resource目录下的路径
     * @return
     */
    public static String getKeyFilePath(String res) {
        return CommUtil.class.getResource(res).getPath();
    }


    /**
     * 判断一个对象是否是基础数据类型或String
     *
     * @param obj
     * @author yaopeng
     * @date 2017/10/31 11:28
     */
    public static boolean isBasicDataType(Object obj) {
        if (obj instanceof String) {
            return true;
        } else if (obj instanceof Integer) {
            return true;
        } else if (obj instanceof Short) {
            return true;
        } else if (obj instanceof Float) {
            return true;
        } else if (obj instanceof Double) {
            return true;
        } else if (obj instanceof Byte) {
            return true;
        } else if (obj instanceof Boolean) {
            return true;
        } else if (obj instanceof Long) {
            return true;
        } else if (obj instanceof Character) {
            return true;
        }
        return false;
    }

    /**
     * 日期（Date）加一天
     *
     * @param date
     * @return
     */
    public static Date addDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }


    /**
     * null、""转"null"
     *
     * @param o
     * @return
     */
    public static String toNullString(Object o) {
        if (!isNotNull(o)) {
            return "null";
        }
        return o.toString();
    }

    /**
     * 生成16进制随机名
     *
     * @return
     */
    public static String hexName() {
        return Long.toHexString(Long.parseLong(randomName()));
    }

    public static String randomName() {
        return dateFormat("MMddHHmmssSSS", LocalDateTime.now()) + genRandomNum(3);
    }

    public static boolean isNullArr(Object[] arr) {
        return null == arr || arr.length == 0 || null == arr[0];
    }
}
