package com.spring.gear.beans.factroy.annotation;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.core.type.AnnotationMetadata;

public interface AnnotatedBeanDefinition extends BeanDefinition{

	AnnotationMetadata getMetadata();
}
