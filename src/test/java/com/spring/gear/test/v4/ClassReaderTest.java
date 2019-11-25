package com.spring.gear.test.v4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;

import com.spring.gear.core.annotation.AnnotationAttributes;
import com.spring.gear.core.io.ClassPathResource;
import com.spring.gear.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.spring.gear.core.type.classreading.ClassMetaReaderingVisitor;
/**
 * 第二步
 * @author hp
 *
 */
public class ClassReaderTest {

	@Test
	public void testGetClasMetaData() throws IOException {
		ClassPathResource resource = new ClassPathResource("com/spring/gear/service/v4/ZooService.class");
		ClassReader reader = new ClassReader(resource.getInputStream());
		ClassMetaReaderingVisitor vistor = new ClassMetaReaderingVisitor();
		//调用accept方法就会调用vistor方法
		reader.accept(vistor, ClassReader.SKIP_DEBUG);
		Assert.assertFalse(vistor.isAbstract());
		Assert.assertFalse(vistor.isInterface());
		Assert.assertFalse(vistor.isFinal());
		Assert.assertEquals("com.spring.gear.service.v4.ZooService", vistor.getClassName());
		Assert.assertEquals("java.lang.Object", vistor.getSuperClassName());
		Assert.assertEquals(0, vistor.getInterfaces().length);
	}
	
	@Test
	public void testGetAnnotation() throws IOException {
		ClassPathResource resource = new ClassPathResource("com/spring/gear/service/v4/ZooService.class");
		ClassReader reader = new ClassReader(resource.getInputStream());
		AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
		//进入父类的vistor方法
		reader.accept(visitor, ClassReader.SKIP_DEBUG);
		String annotation = "com.spring.gear.stereotype.Component";
		assertTrue(visitor.hasAnnotation(annotation));
		AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
		assertEquals("zooService", attributes.get("value"));
		
	}
}
