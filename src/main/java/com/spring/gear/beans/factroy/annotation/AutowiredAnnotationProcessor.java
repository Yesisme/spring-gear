package com.spring.gear.beans.factroy.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import com.spring.gear.beans.factroy.config.AutowireCapableBeanFactory;
import com.spring.gear.beans.factroy.config.InstantiationAwareBeanPostProcessor;
import com.spring.gear.beans.factroy.support.BeanCreationException;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.core.annotation.AnnotationUtils;
import com.spring.gear.utils.ReflectionUtil;

public class AutowiredAnnotationProcessor implements InstantiationAwareBeanPostProcessor {

	private AutowireCapableBeanFactory beanFactory;
	
	private String requiredParameterName = "required";
	
	private boolean requiredParameterValue =true;
	
	private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<Class<? extends Annotation>>();
	
	public AutowiredAnnotationProcessor() {
		this.autowiredAnnotationTypes.add(Autowired.class);
	}

	public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public InjectionMetadata buildAutowiringMetadata(Class<?> clazz) {
		LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();
		Class<?> targetClass = clazz;
		do {
			LinkedList<InjectionElement> currelements = new LinkedList<InjectionElement>();
			for (Field field : targetClass.getDeclaredFields()) {
				Annotation ann = findAutowiredAnnotation(field);
				if(ann!=null) {
					if(Modifier.isStatic(field.getModifiers())) {
						continue;
					}
					boolean required = determineRequiredStatus(ann);
					currelements.add(new AutowiredFieldElememt(field, required, beanFactory));
				}
			}
			for (Method method : targetClass.getMethods()) {
				//TODO
			}
			elements.addAll(0,currelements);
			targetClass = targetClass.getSuperclass();
		}while(targetClass!=null && targetClass!=Object.class);
		
		return new InjectionMetadata(clazz, elements);
	}

	private boolean determineRequiredStatus(Annotation ann) {
		try {
			Method method = ReflectionUtil.findMethod(ann.annotationType(),this.requiredParameterName);
			if(method==null) {
				return true;
			}
			return (this.requiredParameterValue == (Boolean)ReflectionUtil.invokeMethod(method,ann));
		}catch(Exception e) {
			
		}
		return false;
	}

	private Annotation findAutowiredAnnotation(AccessibleObject ao) {
		for (Class<? extends Annotation> type : autowiredAnnotationTypes) {
			Annotation ann = AnnotationUtils.getAnnotation(ao, type);
			if(ann!=null) {
				return ann;
			}
		}
		return null;
	}

	@Override
	public Object beforeInitialization(Object bean, String beanName) {
		return null;
	}

	@Override
	public Object afterInitialization(Object bean, String beanName) {
		return null;
	}

	@Override
	public Object beforeInstantiation(Class<?> beanClass, String beanName) {
		return null;
	}

	@Override
	public boolean afterInstantiation(Object bean, String beanName) {
		return false;
	}

	@Override
	public void postProcessPropertyValue(Object bean, String beanName) {
		InjectionMetadata metadata = buildAutowiringMetadata(bean.getClass());
		try {
			metadata.inject(bean);
		}catch(Exception e) {
			throw new BeanCreationException(beanName+"injected of autowired dependencies failed");
		}
	}
}
