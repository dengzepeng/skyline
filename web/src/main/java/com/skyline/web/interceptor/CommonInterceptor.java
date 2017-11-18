package com.skyline.web.interceptor;

import com.skyline.common.log.CommonLog;
import com.skyline.common.utils.CommUtil;
import com.skyline.common.utils.TidManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 公共拦截器，读取请求来源详情信息
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TidManager.clear();
        CommUtil.getTid();
        // 打印请求参数（包括手机类型）
        CommonLog.access("Request," + getParamStr(request) + ", Headers " + getHeaderStr(request));
        // 设置当前时间
        request.setAttribute("tmp_controllerBgnTime", System.currentTimeMillis());

        return true;
    }


    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long timeCost = 0L;
        try {
            if (request.getAttribute("tmp_controllerBgnTime") != null) {
                timeCost = System.currentTimeMillis() - Long.valueOf(request.getAttribute("tmp_controllerBgnTime").toString());
            }
            // 打印接口请求时间
            CommonLog.access("Response, timecost=" + timeCost + ",url=" + request.getServletPath());
            if (timeCost > 300) {
                // 超过300毫秒的打印出接口
                CommonLog.access("Response timecost too long!, timecost=" + timeCost + " ," + getParamStr(request) + "&userPhone=" + request.getAttribute("tmp_userPhone"));
            }
        } catch (Exception e) {
            // 打印接口请求时间
            CommonLog.access("Response, timecost=" + timeCost + ",url=" + request.getServletPath());
        } finally {
            // 在线程最后清除t_id
            TidManager.clear();
        }
    }

    @SuppressWarnings("unchecked")
    private String getParamStr(HttpServletRequest request) {
        String queryString = "";
        Map<String, String[]> params = request.getParameterMap();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for(String v:values){
                queryString += key + "=" + v + "&";
            }
        }
        String userAgent = StringUtils.trim(request.getHeader("User-Agent"));
        return "url=" + request.getServletPath() + "?" + queryString + "&userAgent=" + userAgent + "&ip=" + CommUtil.getIpAddr(request);
    }

    private String getHeaderStr(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append("appname:").append(request.getHeader("appname"))
                .append(" OS:").append(request.getHeader("OS"))
                .append(" NT:").append(request.getHeader("NT"))
                .append(" UA:").append(request.getHeader("UA"));

        return builder.toString();

    }

}
