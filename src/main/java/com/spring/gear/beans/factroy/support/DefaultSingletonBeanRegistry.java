package com.spring.gear.beans.factroy.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.IllegalAddException;

import com.spring.gear.beans.factroy.config.SingletonBeanRegistry;
import com.spring.gear.utils.Assert;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{
	
	private final Map<String,Object> singletonObjects = new ConcurrentHashMap<>(64);

	@Override
	public void registrySingleton(String beanName, Object singletonObject) {
		Assert.notNull(beanName, "beanName can not be null");
		Object oldObject = this.singletonObjects.get(beanName);
		if(oldObject != null) {
			throw new IllegalStateException("can not registry" +"[" +singletonObject+ "]" +"under beanName" +  beanName+ "there is aleardy Object"+oldObject);
		}
		this.singletonObjects.put(beanName, singletonObject);
		
	}

	@Override
	public Object getSingleton(String beanName) {
		return this.singletonObjects.get(beanName);
	}

}
