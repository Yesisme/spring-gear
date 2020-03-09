package com.spring.gear.test.v1;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.spring.gear.context.ApplicationContext;
import com.spring.gear.context.support.ClassPathXmlApplcationContext;
import com.spring.gear.context.support.FileSystemXmlApplicationContext;
import com.spring.gear.service.v1.ZooService;

public class ApplicationTest {

	@Test
	public void testClassPathXmlApplicationContext() {
		ApplicationContext application = new ClassPathXmlApplcationContext("zoo-v1.xml");
		ZooService zooService = (ZooService)application.getBean("zoo");
		assertNotNull(zooService);
	}
	
	@Test
	public void testFileSystemXmlApplicationContext() {
		ApplicationContext application = new FileSystemXmlApplicationContext("E:\\spring-gear\\zoo-v1.xml");
		ZooService zooService = (ZooService) application.getBean("zoo");
		assertNotNull(zooService);
		
	}
	
	
}
