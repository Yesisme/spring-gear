package com.spring.gear.test.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.spring.gear.beans.factroy.config.RuntimeBeanReference;
import com.spring.gear.beans.factroy.config.TypedStringValue;
import com.spring.gear.beans.factroy.support.BeanDefinitionValueResolver;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.beans.factroy.xml.XmlBeanDefinitionReader;
import com.spring.gear.core.io.ClassPathResource;
import com.spring.gear.dao.v2.AccuntDao;

public class BeanDefinitionValueResolveTest {
	
	
	
	DefaultBeanFactory factory = null;
	XmlBeanDefinitionReader reader = null;
	BeanDefinitionValueResolver resolver = null;
	
	@Before
	public void setUp() {
		factory = new DefaultBeanFactory();
		reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("Zoo-v2.xml"));
		resolver = new BeanDefinitionValueResolver(factory);
		
	}

	@Test
	public void testRuntimeBeanReference() {
		RuntimeBeanReference reference = new RuntimeBeanReference("accuntDao");
		AccuntDao value = (AccuntDao) resolver.resolverValueIfNecessary(reference);
		assertNotNull(value);
		assertTrue(value instanceof AccuntDao);
	}
	
	@Test
	public void testTypedStringValue() {
		TypedStringValue tys = new TypedStringValue("test");
		Object value = resolver.resolverValueIfNecessary(tys);
		assertNotNull(value);
		assertEquals("test", value);
	}
}
