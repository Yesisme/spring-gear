package com.spring.gear.test.v3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.factroy.support.ConstrutorResovler;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.beans.factroy.xml.XmlBeanDefinitionReader;
import com.spring.gear.core.io.ClassPathResource;
import com.spring.gear.service.v3.ZooService;

public class ConstructorResolverTest {

	@Test
	public void testConstructorResolver() {
	DefaultBeanFactory factory = new DefaultBeanFactory();
	XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
	reader.loadBeanDefinition(new ClassPathResource("zoo-v3.xml"));
	BeanDefinition bd = factory.getBeanDefinition("zoo");
	
	ConstrutorResovler resolver = new ConstrutorResovler(factory);
	
	ZooService zooService =(ZooService) resolver.autowireConstructor(bd);
	assertNotNull(zooService.getAccuntDao());
	assertNotNull(zooService.getItemDao());
	assertEquals(2, zooService.getVersion());
	
	}
}
