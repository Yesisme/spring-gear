package com.spring.gear.utils;

public abstract class ClassUtil {

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		
		//获取
		try {
			cl = Thread.currentThread().getContextClassLoader();
		}catch(Exception e) {
			
		}
		
		if(cl == null) {
			cl = ClassUtil.class.getClassLoader();
			
			if(cl==null) {
				try {
					cl = ClassLoader.getSystemClassLoader();
				}catch(Exception e) {
					
				}
			}
		}	
		return cl;
	}
}
