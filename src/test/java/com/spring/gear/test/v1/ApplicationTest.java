package com.spring.gear.test.v1;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.spring.gear.context.ApplicationContext;
import com.spring.gear.context.support.ClassPathXmlApplcationContext;
import com.spring.gear.service.v1.ZooService;

public class ApplicationTest {

	@Test
	public void testGetBean() {
		ApplicationContext application = new ClassPathXmlApplcationContext("zoo-v1.xml");
		ZooService zooService = (ZooService)application.getBean("zoo");
		assertNotNull(zooService);
	}
}
