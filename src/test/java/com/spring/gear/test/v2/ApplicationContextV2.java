package com.spring.gear.test.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.spring.gear.context.ApplicationContext;
import com.spring.gear.context.support.ClassPathXmlApplcationContext;
import com.spring.gear.dao.v2.AccuntDao;
import com.spring.gear.dao.v2.ItemDao;
import com.spring.gear.service.v2.ZooService;


public class ApplicationContextV2 {

	@Test
	public void testApplicationContextV2() {
		ApplicationContext app = new ClassPathXmlApplcationContext("zoo-v2.xml");
		ZooService zoo = (ZooService)app.getBean("zoo");
		assertNotNull(zoo.getAccuntDao());
		assertNotNull(zoo.getItemDao());
		
		assertTrue(zoo.getAccuntDao() instanceof AccuntDao);
		assertTrue(zoo.getItemDao() instanceof ItemDao);
		assertEquals("leym", zoo.getOwner());
		assertEquals(2, zoo.getVersion());
	}
}
