package com.skyline.common.log;

/**
 * 行为类型枚举类
 * @author leo
 *
 */
public enum BehaviorType {
	LOGIN(100),			//登陆
	LOGIN_AUTO(101),	//自动登陆
	LOGIN_OTHER_APP(102),	//第三方APP授权登陆
	LOGIN_OTHER_WAP(103),	//第三方wap授权登陆
	LOGOUT(200),		//注销
	REGISTER(300),		//注册
	REGISTER_OTHER(301),//第三方注册
	SHOPPING_CART(400),	//购物车
	SUBMIT_ORDER(500),	//提交订单
	PAY(600),				//统一支付
	PAY_SOON(601);		//立即支付
	
	private int value;
	
	private BehaviorType(int value){
		this.value = value;
	}

	
	public int getValue() {
		return value;
	}
	
	
}
