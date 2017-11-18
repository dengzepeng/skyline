package com.skyline.common.exception;

import com.skyline.common.constants.message.MessageEnum;

/**
 * 描述：公共异常类，捕获自定义异常信息
 * @author pengyao
 * 创建时间 2017/8/8.
 */
public class SimpleServiceException extends RuntimeException {
	private String code;
	private String errMessage;

	public SimpleServiceException(String code, String errMessage) {
		this.code = code;
		this.errMessage = errMessage;
	}

	public SimpleServiceException(MessageEnum messageEnum){
		this.code = messageEnum.getCode();
		this.errMessage = messageEnum.getMessage();
	}

	public SimpleServiceException(String code,String message,Throwable t){
		super(t);
		this.code=code;
		this.errMessage =message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
}
