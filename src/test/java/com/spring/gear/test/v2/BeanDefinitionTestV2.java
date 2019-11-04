package com.spring.gear.test.v2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.factroy.PropertyValue;
import com.spring.gear.beans.factroy.config.RuntimeBeanReference;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.beans.factroy.xml.XmlBeanDefinitionReader;
import com.spring.gear.core.io.ClassPathResource;

public class BeanDefinitionTestV2 {

	@Test
	public void testBeanDefinition() {
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("zoo-v2.xml"));
		BeanDefinition bd = factory.getBeanDefinition("zoo");
		List<PropertyValue> pvs =  bd.getPropertiesValues();
		assertTrue(pvs.size()==4);
		{
			PropertyValue pv = this.getPropertyValues("accuntDao",pvs);
			assertNotNull(pv);
			Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
		}
		
		{
			PropertyValue pv = this.getPropertyValues("itemDao",pvs);
			assertNotNull(pv);
			Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
		}
		
	}
	private PropertyValue getPropertyValues(String beanName,List<PropertyValue> pvs) {
		for (PropertyValue pv : pvs) {
			if(pv.getName().equals(beanName)) {
				return pv;
			}
		}
		return null;
	}
}
