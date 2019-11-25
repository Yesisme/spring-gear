package com.spring.gear.beans.factroy.support;

import com.spring.gear.beans.BeanDefinition;
/**
 * 按照规则生成bean的名字
 * @author hp
 *
 */
public interface BeanNameGenerator {

	
	String generateBeanName(BeanDefinition definition,BeanDefinitionRegistry registry);
}
