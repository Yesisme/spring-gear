package com.spring.gear.beans.factroy.config;

import java.util.List;

import com.spring.gear.beans.factroy.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory,AutowireCapableBeanFactory{

	public void setClassLoader(ClassLoader classLoader);
	
	public ClassLoader getClassLoader();
	
	void addBeanPostProcessor(BeanPostProcessor postProcessor);
	
	List<BeanPostProcessor> getBeanPostProcessor();
}
