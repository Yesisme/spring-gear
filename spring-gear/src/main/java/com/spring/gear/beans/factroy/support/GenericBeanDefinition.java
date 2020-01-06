package com.spring.gear.beans.factroy.support;

import com.spring.gear.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition{
	
	private String id;
	
	private String className;
	
	public GenericBeanDefinition(String id,String className) {
		this.id = id;
		this.className = className;
	}
	@Override
	public String getBeanClassName() {
		
		return this.className;
	}

}