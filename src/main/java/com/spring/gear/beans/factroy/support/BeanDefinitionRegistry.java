package com.spring.gear.beans.factroy.support;

import com.spring.gear.beans.BeanDefinition;

/**
 * 获取BeanDefinition并注册
 * @author hp
 *
 */
public interface BeanDefinitionRegistry {

	BeanDefinition getBeanDefinition(String BeanId);
	
	void RegistryBeanDefinition(String BeanId,BeanDefinition beanDefinition);
}
