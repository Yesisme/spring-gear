package com.spring.gear.beans.factroy.support;

import com.spring.gear.beans.BeanDefinition;

public interface BeanNameGenerator {

	
	String generateBeanName(BeanDefinition definition,BeanDefinitionRegistry registry);
}
