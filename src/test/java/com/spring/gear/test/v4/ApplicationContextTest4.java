package com.spring.gear.test.v4;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.spring.gear.context.ApplicationContext;
import com.spring.gear.context.support.ClassPathXmlApplcationContext;
import com.spring.gear.service.v4.ZooService;

public class ApplicationContextTest4 {

	@Test
	public void testGetBeanProperty() {
		 ApplicationContext app = new ClassPathXmlApplcationContext("zoo-v4.xml");
		 ZooService zoo = (ZooService) app.getBean("zooService");
		 
		 assertNotNull(zoo.getAccountDao());
		 assertNotNull(zoo.getAccountDao());
		 
	}
}
