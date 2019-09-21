package com.spring.gear.beans.factroy.config;

public interface SingletonBeanRegistry {

	void registrySingleton(String beanName,Object singletonObject);
	
	Object getSingleton(String beanName);
}
