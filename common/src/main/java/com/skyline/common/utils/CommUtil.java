package com.skyline.common.utils;

import com.skyline.common.log.CommonLog;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommUtil {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static Date formatDate(String s) {
		Date d = null;
		try {
			d = dateFormat.parse(s);
		} catch (Exception localException) {}
		return d;
	}

	public static Date formatDate(String s, String format) {
		Date d = null;
		try {
			SimpleDateFormat dFormat = new SimpleDateFormat(format);
			d = dFormat.parse(s);
		} catch (Exception localException) {}
		return d;
	}

	public static boolean checkDateTime(String datetime, String format){
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			df.parse(datetime);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}


	public static String formatTime(String format, Object v) {
		if(v == null) {
			return null;
		}
		if(v.equals("")) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(v);
	}

	public static String formatLongDate(Object v) {
		if((v == null) || (v.equals(""))) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(v);
	}

	public static String formatShortDate(Object v) {
		if(v == null) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(v);
	}



	public static boolean isNotNull(Object obj) {
		if((obj != null) && (!obj.toString().equals(""))&& (!obj.toString().equals("null"))) {
			return true;
		}
		return false;
	}


	public static int null2Int(Object s) {
		int v = 0;
		if(s != null) {
			try {
				v = Integer.parseInt(s.toString());
			} catch (Exception localException) {}
		}
		return v;
	}

	public static float null2Float(Object s) {
		float v = 0.0F;
		if(s != null) {
			try {
				v = Float.parseFloat(s.toString());
			} catch (Exception localException) {}
		}
		return v;
	}

	public static double null2Double(Object s) {
		double v = 0.0D;
		if(s != null) {

			try {
				v = Double.parseDouble(null2String(s));
			} catch (Exception localException) {}
		}

		return v;
	}

	public static boolean null2Boolean(Object s) {
		boolean v = false;
		if(s != null) {
			try {
				v = Boolean.parseBoolean(s.toString());
			} catch (Exception localException) {}
		}
		return v;
	}

	public static String null2String(Object s) {
		return s == null ? "" : s.toString().trim();
	}

	public static Long null2Long(Object s) {
		Long v = Long.valueOf(-1L);
		if(s != null) {
			try {
				v = Long.valueOf(Long.parseLong(s.toString()));
			} catch (Exception localException) {}
		}
		return v;
	}

	public static BigDecimal null2BigDecimal(Object s) {
		if(s != null) {
			try {
				if(s instanceof Double || s instanceof Float
					|| s instanceof Integer || s instanceof Long
					|| s instanceof String) {
					return new BigDecimal(s.toString().trim());
				} else {
					return BigDecimal.ZERO;
				}
			} catch (Exception localException) {}
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
		if(hour > 0) {
			ret = ret + hour + "小时";
		}
		if(minute > 0) {
			ret = ret + minute + "分";
		} else if((minute <= 0) && (seconds > 0)) {
			ret = ret + "零";
		}
		if(seconds > 0) {
			ret = ret + seconds + "秒";
		}
		return ret;
	}

	//秒
	public static String sec2Date(long time) {
		int hour = (int) time / 3600;
		long balance = time - hour * 60 * 60;
		int minute = (int) balance / 60;
		balance -= minute  * 60;
		int seconds = (int) balance;
		String ret = "";
		if(hour > 0) {
			ret = ret + hour + "小时";
		}
		if(minute > 0) {
			ret = ret + minute + "分";
		} else if((minute <= 0) && (seconds > 0)) {
			ret = ret + "零";
		}
		if(seconds > 0) {
			ret = ret + seconds + "秒";
		}
		return ret;
	}

	public static String getIpAddr(HttpServletRequest request) {
        try {
            String ip = request.getHeader("x-forwarded-for");
            if((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getRemoteAddr();
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	    ip = request.getHeader("http_client_ip");
        	}
        	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        	}

            if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
            	//根据网卡取本机配置的IP
            	InetAddress addr = null;
                try {
                    addr = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    CommonLog.error("", e);
                }
                ip = null2String(addr.getHostAddress());
            }
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15
                if(ip.indexOf(",")>0){
                	ip = ip.substring(0,ip.indexOf(","));
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
		if(type.equals("y")) {
			return cal.get(1);
		}
		if(type.equals("M")) {
			return cal.get(2) + 1;
		}
		if(type.equals("d")) {
			return cal.get(5);
		}
		if(type.equals("H")) {
			return cal.get(11);
		}
		if(type.equals("m")) {
			return cal.get(12);
		}
		if(type.equals("s")) {
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
		if(IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String[] s = IP.split("\\.");
			if((Integer.parseInt(s[0]) < 255)
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
		if(isIp(serverName)) {
			system_domain = serverName;
		} else if(serverName.indexOf(".") == serverName.lastIndexOf(".")) {
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

	final static Pattern pattern = Pattern.compile("\\S*[?]\\S*");

	public static String parseSuffix(String url) {

		Matcher matcher = pattern.matcher(url);

		String[] spUrl = url.toString().split("/");
		int len = spUrl.length;
		String endUrl = spUrl[len - 1];

		if(matcher.find()) {
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
		String contextPath = request.getContextPath().equals("/") ? "" : request.getContextPath();
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
        String now = new SimpleDateFormat("yyyyMMddHHmmssSS") .format(new Date());
        //生成三位随机数
        char[] numbers = "0123456789".toCharArray();

        Random randGen = new Random();
        char[] randBuffer = new char[2];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbers[randGen.nextInt(9)];
        }
        //买家版客户端门户系统编码=38
        String t_Id = "728" + now + new String(randBuffer);
        return t_Id;
    }

	public static String getTid(){
		String t_Id = TidManager.getTid();
        if (StringUtils.isNotEmpty(t_Id) && t_Id.startsWith("728")) {
            return t_Id;
        }
        t_Id = genTid();
        // 设置t_id
        TidManager.setTid(t_Id);
		return t_Id;
	}


    public static String convertDateStr4Cgi(String dateStr) {
        try {
        	if (StringUtils.isNotEmpty(dateStr) && dateStr.length()==19) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = format.parse(dateStr);
                format = new SimpleDateFormat("yyyyMMddHHmmss");
                return format.format(date);
            }else if(StringUtils.isNotEmpty(dateStr) && dateStr.length()==16){
            	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = format.parse(dateStr);
                format = new SimpleDateFormat("yyyyMMddHHmmss");
                return format.format(date);
            }
        } catch (Exception e) {
            CommonLog.error("", e);
        }
        return null;
    }
    /**
     * date to String(转化格式"yyyyMMddHHmmss")
     * @param date
     * @return
     */
    public static String converDatatoString(Date date){
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    	String dataStr = null;
    	try{
    		if(null != date){
    			dataStr = format.format(date);
    		}
    	}catch(Exception e){
    		CommonLog.error("date transform error", e);
    	}
		return dataStr;
    }

    public static Date convertStringToDate(String str){
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    	try{
    		if(!StringUtils.isEmpty(str)){
    			return format.parse(str);
    		}
    	}catch(Exception e){
    		CommonLog.error("date transform error", e);
    	}
		return null;
    }

    /**
     * 获取生成token的UUID随机数，生成k值
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
     * 判断是否为APP端请求
     * @param request
     * @return 返回true表示为wap发送的请求
     */
	public static boolean isWapRequest(HttpServletRequest request) {
		String appname = request.getHeader("appname");
		if(StringUtils.isEmpty(appname)){
			appname = request.getParameter("appname");
		}
		if (StringUtils.isEmpty(appname)) {
	        return true;
	    }
	    if (appname.toLowerCase().contains("ios")) {//to check
	        return false;
	    } else if (appname.toLowerCase().contains("android")) {//to check
	        return false;
	    } else if (appname.toLowerCase().contains("wap")) {//wap
	        return true;
	    } else if (appname.toLowerCase().contains("wx")) {//微信
	        return true;
	    } else if(appname.toLowerCase().contains("dj")){//点金
	    	return true;
	    }
		return true;
	}

	/**
	 * 验证手机号码格式是否正确
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		Pattern pattern = Pattern.compile("^1\\d{10}");
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}


	/***
	 * 生成指定位数的随机数
	 * @param len
	 * @return
	 */
	public static String genRandomNum(int len){
	    //35是因为数组是从0开始的，26个字母+10个数字
	    final int maxNum = 36;
	    int i; //生成的随机数
	    int count = 0; //生成的密码的长度
	    char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	    StringBuffer pwd = new StringBuffer("");
	    Random r = new Random();
	    while(count < len){
	     //生成随机数，取绝对值，防止生成负数，
	     i = Math.abs(r.nextInt(maxNum)); //生成的数最大为36-1
	     if (i >= 0 && i < str.length) {
	      pwd.append(str[i]);
	      count ++;
	     }
	    }
	    return pwd.toString();
	 }


	/**
	 * 四舍五入
	 * @param num
	 * @param scale
	 * @return
	 */
	 public static double round(double num ,int scale) {
	        BigDecimal big  = new BigDecimal(num);
	        BigDecimal result = big.divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP);
	        return result.doubleValue();
	 }

	 /**
	  * 转化期望送货时间
	  * @param expect_time
	  * @return
	  */
	public static String getExceptime(String expect_time) {
		String expectTime = "";
		try{
			if(CommUtil.isNotNull(expect_time)){
				String startTime = "";
				String endTime = "";
				String day = "";
				String[] time = expect_time.split("_");
				if(CommUtil.isNotNull(time[0])){
					String[] dayTime = time[0].split(" ");
					day = dayTime[0];
					startTime = dayTime[1].substring(0,5);
				}
				if(CommUtil.isNotNull(time[1])){
					String[] dayTime1 = time[1].split(" ");
					endTime = dayTime1[1].substring(0,5);
				}
				expectTime = day+" "+startTime+"-"+endTime;
				//System.out.println(expectTime);
			}
		}catch(Exception e){
			CommonLog.error("期望送货时间转化异常", e);
			return "";
		}
		return expectTime;
	}

	/**
	 * 判断微信端请求
	 * @param request
	 * @return
	 */
	public static boolean isWXRequest(HttpServletRequest request) {
		String appname = request.getHeader("appname");
		if(StringUtils.isEmpty(appname)){
			appname = request.getParameter("appname");
		}
		if (StringUtils.isEmpty(appname)) {
	        return false;
	    }
	    if (appname.toLowerCase().contains("wx") ) {//微信
	        return true;
	    }
		return false;
	}

	/**
	 * 校验时间是否在配送范围内
	 * @param startTime
	 * @param toHomeStartTime
	 * @return
	 */
	public static boolean checkToHomeTime(String startTime,
			String toHomeStartTime) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		try {
			long stime = df.parse(startTime).getTime();
			long toTime = df.parse(toHomeStartTime).getTime();
			if((stime-toTime)>=0){
				return true;
			}
		} catch (ParseException e) {
			CommonLog.error("时间格式转化错误 startTime="+startTime +"toHomeStartTime="+toHomeStartTime, e);
			e.printStackTrace();
		}
		return false;
	}



	public static String convertHtmlToDegree(String str){
		if(StringUtils.isNotBlank(str)){
		    return str.replace("&deg;", "°");
		}
        return str;
	}


	/**
	 * 将空字符串设置返回""
	 * @return
	 */
	public static String toSetEmptyValue(Object obj){
		if(CommUtil.isNotNull(obj)){
			return obj.toString();
		}else{
			return "";
		}
	}


	/**
	 * 格式化一个数字为指定格式的bigdecimal
	 * @return
	 */
	public static BigDecimal formatBigDecimal(String number, String format){
		DecimalFormat df = new DecimalFormat(format);
		return  new BigDecimal(df.format(Double.valueOf(number)));
	}

	public static void main(String[] args){
		System.out.println(formatBigDecimal("12.1","0.00"));
		System.out.println(sec2Date(3900));

	}

	/**
	 * 检查是否有重复的卡券
	 * @param coupon
	 * @return
	 */
	public static boolean isRepeatedCoupon(String coupon) {
		String[] coupons = coupon.split(",");
		Map<String,String> map = new HashMap<String, String>();
		if(null != coupons && coupons.length>0){
			for(int i=0;i<coupons.length;i++){
				if(map.containsKey(coupons[i])){
					return true;
				}else{
					map.put(coupons[i], coupons[i]);
				}
			}
		}
		return false;
	}

	/**
	 * 生成16位的数字订单号
	 * @return
	 */
	public static String genOutTradeNo(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date()).concat(genRandomNum(8));
	}

	/**
	 *
	 * @param res 文件在resource目录下的路径
	 * @return
	 */
	public static String getKeyFilePath(String res){
		return CommUtil.class.getResource(res).getPath();
	}


	/**
	 * 判断一个对象是否是基础数据类型或String
	 * @author yaopeng
	 * @date 2017/10/31 11:28
	 *
	 * @param obj
	 */
	public static boolean isBasicDataType(Object obj){
		if(obj instanceof String){
		    return true;
        }else if(obj instanceof Integer){
            return true;
        }else if(obj instanceof Short){
            return true;
        }else if(obj instanceof Float){
            return true;
        }else if(obj instanceof Double){
            return true;
        }else if(obj instanceof Byte){
            return true;
        }else if(obj instanceof Boolean){
            return true;
        }else if(obj instanceof Long){
            return true;
        }else if(obj instanceof Character){
            return true;
        }
		return false;
	}

}
