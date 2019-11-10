package com.spring.gear.test.v4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.spring.gear.beans.factroy.annotation.AutowiredAnnotationProcessor;
import com.spring.gear.beans.factroy.annotation.AutowiredFieldElememt;
import com.spring.gear.beans.factroy.annotation.InjectionElement;
import com.spring.gear.beans.factroy.annotation.InjectionMetadata;
import com.spring.gear.beans.factroy.config.DependencyDescriptor;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.dao.v4.AccountDao;
import com.spring.gear.dao.v4.ItemDao;
import com.spring.gear.service.v4.ZooService;

public class AutowireAnnotationProcessorTest {


	AccountDao accountDao = new AccountDao();
	ItemDao itemDao = new ItemDao();
	
	DefaultBeanFactory beanFactory = new DefaultBeanFactory() {
		public Object resolveDependency(DependencyDescriptor descriptor) {
			if(descriptor.getFieldType().equals(AccountDao.class)) {
				return accountDao;
			}
			
			if(descriptor.getFieldType().equals(ItemDao.class)) {
				return itemDao;
			}
			throw new RuntimeException("cannot has want to type");
		}	
	};
	
	@Test
	public void testGetInjectionMetadata() {
		AutowiredAnnotationProcessor processor = new AutowiredAnnotationProcessor();
		processor.setBeanFactory(beanFactory);
		InjectionMetadata injectionMetadata =processor.buildAutowiringMetadata(ZooService.class);
		List<InjectionElement> elements = injectionMetadata.getInjectionElement();
		assertEquals(2, elements.size());
		assertFieldExists(elements,"accountDao");
		assertFieldExists(elements,"itemDao");
		ZooService zooService = new ZooService();
		injectionMetadata.inject(zooService);
		assertTrue(zooService.getAccountDao() instanceof AccountDao);
		assertTrue(zooService.getItemDao() instanceof ItemDao);
	}
	
	private void assertFieldExists(List<InjectionElement> elements,String fileNames) {
		for (InjectionElement ele : elements) {
			AutowiredFieldElememt fieldEle = (AutowiredFieldElememt)ele;
			Field f = fieldEle.getField();
			if(f.getName().equals(fileNames)) {
				return ;
			}
		}
		Assert.fail(fileNames+"does not exist");
	}
	
	
}
