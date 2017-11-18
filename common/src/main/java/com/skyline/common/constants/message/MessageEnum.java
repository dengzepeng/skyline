package com.skyline.common.constants.message;

/**
 * 错误信息枚举类
 * @author skyline
 *
 */
public enum MessageEnum {

	/**
	 * 错误信息
	 */

	//系统
	SYSTEM_EXCEPTION("70000","系统发生异常"),
	VALIDATE_ERROR("70001","参数校验不通过"),


	//登录
	USERNAME_NOT_EXISTED("90001","用户名不存在"),
	ERROR_USER_PASSWORD("90002","用户密码错误"),

	//注册









	SYSTEM_ERROR("91100","系统繁忙，请稍后再试"),
    USERNAME_PWD_NULL("90000","用户名或密码为空"),
	ENTERED_PASSWORDS_DIFFER("90003","两次输入的密码不一致"),

	TOKEN_INVALID("90005","token验证不通过"),
	CODE_TIME_OUT("90006","验证码失效"),
	CODE_ERROR("90007","验证码错误"),
	SECURITY_ERROR("90008","密保问题答案错误"),
	BY56_REG_ERROR("90009","百运网注册失败"),
	
	USERMAIL_VERIFY_INVALID("90010","邮箱验证失效"),
	USERMAIL_BIND_ERROR("90011","邮箱绑定失败"),
	
	BANKCARD_EXISTED("90012","银行卡已经被绑定"),
	BANKCARD_NOT_EXISTED("90013","银行卡不存在"),
	
	USER_EXISTED("90014","用户已存在"),
	USER_NOT_EXISTED("90015","用户不存在"),
	
	CREDIT_NOT_STANDARD("90016","信用积分未达到标准"),
	CREDIT_QUOTA_ERROR("90017","信用额度错误"),
	
	ACC_SETTING_INCOMPLETE("90018","未完成账户设置"),
	ACC_SUB_NOT_EXISTE("90019","子账户不存在"),
	
	TRANSACTION_FAILURE("90020","交易失败"),
	BALANCE_NOT_ENOUGH("90021","余额不足"),
	
	MOBILE_IS_NULL("90022","手机号为空"),
	
	USERMAIL_EXISTED("90023","该邮箱已经被绑定"),
	
	TRAN_PASSWORD_CHECK_FAILURE("90101","支付密码必须是6-20位数字字母字符"),
	OLD_TRAN_PASSWORD_ERROR("90102","旧支付密码错误"),
	TRAN_PASSWORD_ERROR_LIMIT("90103","支付密码已被锁定"),
	LOGIN_LOCKED("90104","登录被锁定"),
	TRAN_PASSWORD_ERROR_LOCKED("90105","支付密码已被锁定，3小时后自动解锁"),

	DATABASE_OP_ERROR("99990","数据库操作失败"),
	PARAMETER_CHECK_FAIL("99999","参数校验失败"),
	
	
	//================文件上传====================
	FILE_TYPE_NULL("91000","文件类型为空"),
	UPLOAD_FILE_NULL("91001","上传文件为空"),
	AVATAR_UPLOAD_ERROR("91002","头像上传异常"),
	BIZ_LICENSE_UPLOAD_ERROR("91003","营业执照上传异常"),
	FILE_NOT_EXIST("91004","文件不存在"),
	
	//================密码强度====================
	PASSWORD_STRENGTH_WEAK("92000","密码强度弱"),
	PASSWORD_STRENGTH_STRONG("92001","密码强度强"), 
	
	//================加密解密====================
	VALIDATE_SINGN_ERROR("93000","校验签名失败"),
	
	/**
	 * 提示信息
	 */
	LOGIN_NAME_UNREG("70001" ,"该登录名未注册"),
	IOU_NOT_SUPPORT("70002", "运条暂不向物流供应商提供！");
	
	
	private String code;
	private String message;
	
	private MessageEnum(String code, String message) {
		this.code = code;
		this.message = message;
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
	
}
