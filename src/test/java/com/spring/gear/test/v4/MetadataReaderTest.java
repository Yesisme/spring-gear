package com.spring.gear.test.v4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.spring.gear.core.annotation.AnnotationAttributes;
import com.spring.gear.core.io.ClassPathResource;
import com.spring.gear.core.type.AnnotationMetadata;
import com.spring.gear.core.type.classreading.MetadataReader;
import com.spring.gear.core.type.classreading.SimpleMetaDataReader;
import com.spring.gear.stereotype.Component;

public class MetadataReaderTest {

	
	@Test
	public void testGetMetadata() throws Exception {
		ClassPathResource resource = new ClassPathResource("com/spring/gear/service/v4/ZooService.class");
		
		MetadataReader reader = new SimpleMetaDataReader(resource);
		
		AnnotationMetadata amd = reader.getAnotationMetadata();
		
		String annotation = Component.class.getName();
		
		assertTrue(amd.hasAnnotation(annotation));
		AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
		assertEquals("zooService", attributes.get("value"));
		assertFalse(amd.isAbstract());
		assertFalse(amd.isFinal());
		assertEquals("com.spring.gear.service.v4.ZooService", amd.getClassName());
	}
}
