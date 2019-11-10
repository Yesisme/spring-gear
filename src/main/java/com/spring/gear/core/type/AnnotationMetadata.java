package com.spring.gear.core.type;

import java.util.Set;

import com.spring.gear.core.annotation.AnnotationAttributes;

public interface AnnotationMetadata extends ClassMetadata{

	Set<String> getAnnotationTypes();
	
	boolean hasAnnotation(String annotationType);
	
	public AnnotationAttributes getAnnotationAttributes(String annotationType);
}
