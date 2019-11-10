package com.spring.gear.beans.factroy.config;

public interface BeanPostProcessor {

	Object beforeInitialization(Object bean,String beanName);
	
	Object afterInitialization(Object bean,String beanName);
}
