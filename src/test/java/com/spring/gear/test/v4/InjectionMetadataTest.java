package com.spring.gear.test.v4;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.LinkedList;

import org.junit.Test;

import com.spring.gear.beans.factroy.annotation.AutowiredFieldElememt;
import com.spring.gear.beans.factroy.annotation.InjectionElement;
import com.spring.gear.beans.factroy.annotation.InjectionMetadata;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.beans.factroy.xml.XmlBeanDefinitionReader;
import com.spring.gear.core.io.ClassPathResource;
import com.spring.gear.dao.v4.AccountDao;
import com.spring.gear.dao.v4.ItemDao;
import com.spring.gear.service.v4.ZooService;

public class InjectionMetadataTest {


	@Test
	public void testInjectionMetadata() throws Exception{
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("zoo-v4.xml"));
		Class<?> clz = ZooService.class;
		LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();
		
		{
			Field f = ZooService.class.getDeclaredField("accountDao");
			InjectionElement injectionElem = new AutowiredFieldElememt(f,true,factory);
			elements.add(injectionElem);
		}
		{
			Field f = ZooService.class.getDeclaredField("itemDao");
			InjectionElement injectionElem = new AutowiredFieldElememt(f,true,factory);
			elements.add(injectionElem);
		}
			InjectionMetadata metadata = new InjectionMetadata(clz,elements);
			ZooService zooService = new ZooService();
			metadata.inject(zooService);
			assertTrue(zooService.getAccountDao() instanceof AccountDao);
			assertTrue(zooService.getItemDao() instanceof ItemDao);
			
		}
}		