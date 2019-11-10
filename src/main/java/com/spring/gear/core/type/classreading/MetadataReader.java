package com.spring.gear.core.type.classreading;

import com.spring.gear.core.io.Resource;
import com.spring.gear.core.type.AnnotationMetadata;
import com.spring.gear.core.type.ClassMetadata;

public interface MetadataReader {

	Resource getResource();
	
	ClassMetadata getClassMetadata();
	
	AnnotationMetadata getAnotationMetadata();
	
}
