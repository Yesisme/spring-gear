package com.spring.gear.beans.factroy.config;

import com.spring.gear.beans.factroy.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory{

	public void setClassLoader(ClassLoader classLoader);
	
	public ClassLoader getClassLoader();
}
