package com.iit.asdcw2.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseMessage {
	public static Map<String,String> message(String text){
		Map<String,String> message = new HashMap<>();
		message.put("Message", text);
		return message;
	}
}
