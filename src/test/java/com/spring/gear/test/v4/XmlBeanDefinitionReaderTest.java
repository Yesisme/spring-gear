package com.spring.gear.test.v4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.beans.factroy.xml.XmlBeanDefinitionReader;
import com.spring.gear.context.annotation.ClassPathBeanDefinitionScanner;
import com.spring.gear.context.annotation.ScannedGenericBeanDefinition;
import com.spring.gear.core.annotation.AnnotationAttributes;
import com.spring.gear.core.io.ClassPathResource;
import com.spring.gear.core.io.Resource;
import com.spring.gear.core.type.AnnotationMetadata;
import com.spring.gear.stereotype.Component;

public class XmlBeanDefinitionReaderTest {

	@Test
	public void testXmlBeanDefinitionReader() {
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("zoo-v4.xml");
		reader.loadBeanDefinition(resource);
		String annotation =Component.class.getName();
		
		{
			BeanDefinition bd = factory.getBeanDefinition("zooService");
			assertTrue(bd instanceof ScannedGenericBeanDefinition);
			ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
			AnnotationMetadata amd = sbd.getMetadata();
			assertTrue(amd.hasAnnotation(annotation));
			AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
			assertEquals("zooService", attributes.get("value"));;
		}
		
		{
			BeanDefinition bd = factory.getBeanDefinition("accountDao");
			assertTrue(bd instanceof ScannedGenericBeanDefinition);
			ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
			AnnotationMetadata amd = sbd.getMetadata();
			assertTrue(amd.hasAnnotation(annotation));
			AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
		}
		
		{
			BeanDefinition bd = factory.getBeanDefinition("itemDao");
			assertTrue(bd instanceof ScannedGenericBeanDefinition);
			ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
			AnnotationMetadata amd = sbd.getMetadata();
			assertTrue(amd.hasAnnotation(annotation));
			AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
		}
	}
	
	
}
