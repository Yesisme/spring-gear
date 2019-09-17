package com.spring.gear.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.factroy.BeanDifinitionStoreException;
import com.spring.gear.beans.factroy.BeanFactory;
import com.spring.gear.beans.factroy.support.BeanCreationException;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.service.v1.ZooService;

public class BeanFactoryTest {

	@Test
	public void testGetBean() {
		//1. new一个DefaultBeanFactory继承接口BeanFactory
			BeanFactory factory = new DefaultBeanFactory("zoo-v1.xml");
		//2. DefaultBeanFactory类中实现BeanDefinition
			BeanDefinition bd = factory.getBeanDefinition("zoo");
		//3. 在BeanDefinition中新建一个getBeanClassName
			assertEquals("com.spring.gear.service.v1.ZooService", bd.getBeanClassName());
		//4. DefaultBeanFactory中建立一个getBean方法,返回ZooService
			ZooService zooService = (ZooService)factory.getBean("zoo");
		//5.判断ZooService是否为空
			assertNotNull(zooService);
	}
	
	@Test
	public void testInvalidBean() {
		BeanFactory factory = new DefaultBeanFactory("zoo-v1.xml");
		try {
			factory.getBean("invalidBean");
		}catch(BeanCreationException e) {
			return;
		}
		Assert.fail("this test is fail");
	}
	
	@Test
	public void testInvalidXml() {
		try {
			BeanFactory factory = new DefaultBeanFactory("xx.xml");
		}catch(BeanDifinitionStoreException e) {
			return;
		}
		Assert.fail("this test is fail");
	}
}
