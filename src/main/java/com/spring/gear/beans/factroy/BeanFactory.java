package com.spring.gear.beans.factroy;

import com.spring.gear.beans.BeanDefinition;

public interface BeanFactory {

	Object getBean(String BeanId);

	BeanDefinition getBeanDefinition(String string);

}
