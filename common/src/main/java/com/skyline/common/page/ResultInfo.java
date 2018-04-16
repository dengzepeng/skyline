package com.skyline.common.page;


import com.skyline.common.constants.message.MessageEnum;

import java.io.Serializable;

import static com.skyline.common.page.MessageConstant.FAILURE;
import static com.skyline.common.page.MessageConstant.SUCCESS;

/**
 * 描述：封装返回信息
 *
 * @author skyline
 * 创建时间：2017年6月16日
 */
public class ResultInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private Object data;

    public ResultInfo() {
    }


    public ResultInfo(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    /**
     * 返回json对象
     *
     * @return
     */
    public static ResultInfo success() {
        return new ResultInfo(SUCCESS, "成功", null);
    }

    /**
     * 返回json对象
     *
     * @param data
     * @return
     */
    public static ResultInfo success(Object data) {
        return new ResultInfo(SUCCESS, "成功", data);
    }

    /**
     * 返回json对象
     *
     * @return
     */
    public static ResultInfo failure() {
        return new ResultInfo(FAILURE, "失败", null);
    }


    @Override
    public String toString() {
        return "[code=" + code + ", message=" + message + ", data=" + data + "]";
    }

    /**
     * @param code
     * @param message
     * @return
     * @desc 返回json
     */
    public static ResultInfo failure(String code, String message) {
        return new ResultInfo(code, message, null);
    }

    public static ResultInfo success(String code, String message) {
        return new ResultInfo(code, message, null);
    }

    public static ResultInfo failure(String code, String message, Object data) {
        return new ResultInfo(code, message, data);
    }

    public static ResultInfo response(MessageEnum message) {
        return new ResultInfo(message.getCode(), message.getMessage(), null);
    }
}
