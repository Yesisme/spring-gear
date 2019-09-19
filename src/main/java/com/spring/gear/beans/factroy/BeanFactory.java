package com.spring.gear.beans.factroy;

public interface BeanFactory {

	Object getBean(String BeanId);

	//转移到BeanDefinitionRegistry中,并由其子接口实现
	//BeanDefinition getBeanDefinition(String BeanId);

}
