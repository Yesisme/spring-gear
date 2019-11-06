package com.spring.gear.test.v3;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.ConstructorArgument;
import com.spring.gear.beans.ConstructorArgument.ValueHolder;
import com.spring.gear.beans.factroy.config.RuntimeBeanReference;
import com.spring.gear.beans.factroy.config.TypedStringValue;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.beans.factroy.xml.XmlBeanDefinitionReader;
import com.spring.gear.core.io.ClassPathResource;
import com.spring.gear.core.io.Resource;

public class ConstructirArgumentTest {

	@Test
	public void testConstructorArgument() {
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("zoo-v3.xml");
		reader.loadBeanDefinition(resource);
		BeanDefinition bd = factory.getBeanDefinition("zoo");
		assertEquals(bd.getBeanClassName(), "com.spring.gear.service.v3.ZooService");
		ConstructorArgument args = bd.getConstructorArgument(); 
		List<ValueHolder> valueHolders = args.getArgumentValues();
		assertEquals(3, valueHolders.size());
		
		RuntimeBeanReference ref1 = (RuntimeBeanReference) valueHolders.get(0). getValue();
		assertEquals("accuntDao",ref1.getBeanName());
		
		RuntimeBeanReference ref2 = (RuntimeBeanReference) valueHolders.get(1).getValue();
		
		assertEquals("itemDao",ref2.getBeanName());
		
		TypedStringValue tpy = (TypedStringValue) valueHolders.get(2).getValue();
		assertEquals("2", tpy.getValue());
		
	}
}
