package com.spring.gear.context.support;

import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.beans.factroy.xml.XmlBeanDefinitionReader;
import com.spring.gear.context.ApplicationContext;

public class ClassPathXmlApplcationContext implements ApplicationContext{
	
	private DefaultBeanFactory factory = null;
	
	public ClassPathXmlApplcationContext(String configFileName) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(configFileName);
	}

	@Override
	public Object getBean(String BeanId) {
		
		return factory.getBean(BeanId);
	}


}
