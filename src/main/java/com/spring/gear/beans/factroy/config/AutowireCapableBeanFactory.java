package com.spring.gear.beans.factroy.config;

import com.spring.gear.beans.factroy.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory{

	public Object resolveDependency(DependencyDescriptor descriptor) throws ClassNotFoundException;
}
