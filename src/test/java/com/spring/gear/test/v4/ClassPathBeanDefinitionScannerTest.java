package com.spring.gear.test.v4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.context.annotation.ClassPathBeanDefinitionScanner;
import com.spring.gear.context.annotation.ScannedGenericBeanDefinition;
import com.spring.gear.core.annotation.AnnotationAttributes;
import com.spring.gear.core.type.AnnotationMetadata;
import com.spring.gear.stereotype.Component;
/**
 * 第四步，对指定package进行扫描获取到有注解的类，并创建ScannedGenericBeanDefinition，注册到BeanDefinition中
 * @author hp
 *
 */
public class ClassPathBeanDefinitionScannerTest {

	@Test
	public void testParseScannedBean() {
		DefaultBeanFactory factory = new DefaultBeanFactory();
		String basepackage = "com.spring.gear.service.v4,com.spring.gear.dao.v4";
		
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
		scanner.doScan(basepackage);
		
		String annotation = Component.class.getName();
		
		{
			BeanDefinition bd = factory.getBeanDefinition("zooService");
			assertTrue(bd instanceof ScannedGenericBeanDefinition);
			ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
			AnnotationMetadata amd = sbd.getMetadata();
			assertTrue(amd.hasAnnotation(annotation));
			AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
			assertEquals("zooService", attributes.get("value"));
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
