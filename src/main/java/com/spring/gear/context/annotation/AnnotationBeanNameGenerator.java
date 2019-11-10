package com.spring.gear.context.annotation;

import java.beans.Introspector;
import java.util.Set;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.factroy.annotation.AnnotatedBeanDefinition;
import com.spring.gear.beans.factroy.support.BeanDefinitionRegistry;
import com.spring.gear.beans.factroy.support.BeanNameGenerator;
import com.spring.gear.core.annotation.AnnotationAttributes;
import com.spring.gear.core.type.AnnotationMetadata;
import com.spring.gear.utils.ClassUtil;
import com.spring.gear.utils.StringUtil;

/*
 * componet注解 beanID生成规则具体实现类
 */
public class AnnotationBeanNameGenerator implements BeanNameGenerator {

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		if(definition instanceof AnnotatedBeanDefinition) {
			String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition)definition);
			if(StringUtil.hasText(beanName)) {
				return beanName;
			}
		}
		return buildDefaultBeanName(definition,registry);
	}

	private String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		
		return buildDefaultBeanName(definition);
	}

	private String buildDefaultBeanName(BeanDefinition definition) {
		String shortClassName = ClassUtil.getShortName(definition.getBeanClassName());
		return Introspector.decapitalize(shortClassName);
	}

	private String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
		AnnotationMetadata amd = annotatedDef.getMetadata();
		Set<String> types = amd.getAnnotationTypes();
		String beanName = null;
		for (String type : types) {
			AnnotationAttributes attribute = amd.getAnnotationAttributes(type);
			Object value = attribute.get("value");
			if(value instanceof String) {
				String strValue = (String)value;
				if(StringUtil.hasLength(strValue)) {
					beanName = strValue;
				}
			}
		}
		return beanName;
	}

}
