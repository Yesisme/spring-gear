package com.spring.gear.test.v4;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;

import com.spring.gear.beans.factroy.config.DependencyDescriptor;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.beans.factroy.xml.XmlBeanDefinitionReader;
import com.spring.gear.core.io.ClassPathResource;
import com.spring.gear.dao.v4.AccountDao;
import com.spring.gear.service.v4.ZooService;

public class DependencyDescriptorTest {

	@Test
	public  void testDependencyDescriptor() throws Exception {
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("zoo-v4.xml"));
		
		Field f = ZooService.class.getDeclaredField("accountDao");
		DependencyDescriptor descriptor = new DependencyDescriptor(f,true);
		Object o = factory.resolveDependency(descriptor);
		assertTrue(o instanceof AccountDao);
	}
}
