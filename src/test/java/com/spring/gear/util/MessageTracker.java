package com.spring.gear.util;

import java.util.ArrayList;
import java.util.List;

public class MessageTracker {

	private static List<String> MESSAGES = new ArrayList<>();
	
	public static void setMessage(String msg) {
		MESSAGES.add(msg);
	}
	
	public static List<String> getMessage(){
		return MESSAGES;
	}
	
	public static void cleanMessage() {
		MESSAGES.clear();
	}
	
}
