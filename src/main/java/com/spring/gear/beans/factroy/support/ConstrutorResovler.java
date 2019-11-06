package com.spring.gear.beans.factroy.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.factroy.BeanFactory;

public class ConstrutorResovler {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private final BeanFactory beanFactory;
	
	public ConstrutorResovler(BeanFactory beanFactory) {
		this.beanFactory =  beanFactory;
	}
	
	public Object autowireConstructor(final BeanDefinition bd) {
		return null;
	}
	
}
