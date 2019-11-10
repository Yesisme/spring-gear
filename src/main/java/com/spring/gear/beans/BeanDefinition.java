package com.spring.gear.beans;

import java.util.List;

import com.spring.gear.beans.factroy.PropertyValue;

public interface BeanDefinition {
	
	public static final String SCOPE_SINGLETON = "singleton";
	public static final String SCOPE_PROTOTYPE = "protoType";
	public static final String SCOPE_DEFAULT ="";
	
	public boolean isSingleton();
	public boolean isPropotype();
	
	String getScope();
	void setScope(String scope);
	
	String getBeanClassName();
	
	public List<PropertyValue> getPropertiesValues();
	
	public ConstructorArgument getConstructorArgument();
	
	public boolean hasConstrucotrArgumentValue();
	public String getId();
	public boolean hasBeanClass();
	public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException;
	public Class<?> getBeanClass();
	
	
}
