package com.spring.gear.test.v3;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.spring.gear.context.ApplicationContext;
import com.spring.gear.context.support.ClassPathXmlApplcationContext;
import com.spring.gear.dao.v3.AccuntDao;
import com.spring.gear.dao.v3.ItemDao;
import com.spring.gear.service.v3.ZooService;

public class ApplicationContextV3 {

	@Test
	public void testApplicationContext() {
		ApplicationContext app = new ClassPathXmlApplcationContext("zoo-v3");
		ZooService zooService = (ZooService)app.getBean("zoo");
		
		assertNotNull(zooService.getAccuntDao());
		assertNotNull(zooService.getItemDao());
		assertNotNull(zooService.getVersion());
		
		assertTrue(zooService.getAccuntDao() instanceof AccuntDao);
		assertTrue(zooService.getItemDao() instanceof ItemDao);
	}
}
