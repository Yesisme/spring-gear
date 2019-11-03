package com.spring.gear.beans.factroy.support;

import com.spring.gear.beans.factroy.config.RuntimeBeanReference;
import com.spring.gear.beans.factroy.config.TypedStringValue;

public class BeanDefinitionValueResolver {

	private final DefaultBeanFactory factory;
	
	public BeanDefinitionValueResolver(DefaultBeanFactory factory) {
		this.factory = factory;
	}
	
	
	public Object resolverValueIfNecessary(Object value) {
	
		if(value instanceof RuntimeBeanReference) {
			RuntimeBeanReference reference = (RuntimeBeanReference) value;
			String beanName = reference.getBeanName();
			Object bean = this.factory.getBean(beanName);
			return bean;
		}else if(value instanceof TypedStringValue) {
			TypedStringValue tys = (TypedStringValue) value;
			return tys.getValue();
		}else {
			throw new RuntimeException("the value" +value +"has not implements");
		}
	}
}
