package com.spring.gear.context.annotation;

import com.spring.gear.beans.factroy.annotation.AnnotatedBeanDefinition;
import com.spring.gear.beans.factroy.support.GenericBeanDefinition;
import com.spring.gear.core.type.AnnotationMetadata;
/**
 * 就是一个来存储扫除是componet注解的Bean
 * @author hp
 *
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition{
	
	private final AnnotationMetadata metadata;

	public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
		super();
		this.metadata = metadata;
		setBeanClassName(this.metadata.getClassName());
	}

	public AnnotationMetadata getMetadata() {
		return metadata;
	}

}
