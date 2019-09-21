package com.spring.gear.beans;

public interface BeanDefinition {
	
	public static final String SCOPE_SINGLETON = "singleton";
	public static final String SCOPE_PROTOTYPE = "protoType";
	public static final String SCOPE_DEFAULT ="";
	
	public boolean isSingleton();
	public boolean isPropotype();
	
	String getScope();
	void setScope(String scope);
	
	String getBeanClassName();
}
