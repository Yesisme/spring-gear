package com.spring.gear.test.v5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.spring.gear.context.ApplicationContext;
import com.spring.gear.context.support.ClassPathXmlApplcationContext;
import com.spring.gear.service.v5.ZooService;
import com.spring.gear.util.MessageTracker;

public class ApplicationConextTest {

	@Test
	public void testApplicationContext() {
		ApplicationContext context = new ClassPathXmlApplcationContext("zoo-v5.xml");
		ZooService zooSerivce = (ZooService) context.getBean("zooService");
		
		assertNotNull(zooSerivce.getAccountDao());
		assertNotNull(zooSerivce.getItemDao());
		
		zooSerivce.placeOrder();
		List<String> messageTracker = MessageTracker.getMessage();
		assertEquals(3,messageTracker.size());
		assertEquals("start tx",messageTracker.get(0));
		assertEquals("place order",messageTracker.get(1));
		assertEquals("commit tx",messageTracker.get(2));
	}
}
