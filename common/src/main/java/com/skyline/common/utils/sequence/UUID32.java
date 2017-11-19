package com.skyline.common.utils.sequence;
import java.util.UUID;


public class UUID32 {
	public static synchronized String uuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
