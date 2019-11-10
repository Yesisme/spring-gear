package com.spring.gear.beans.factroy.annotation;

import java.lang.reflect.Field;

import com.spring.gear.beans.factroy.config.AutowireCapableBeanFactory;
import com.spring.gear.beans.factroy.config.DependencyDescriptor;
import com.spring.gear.beans.factroy.support.BeanCreationException;
import com.spring.gear.utils.ReflectionUtil;
/**
 * 对属性进行反射注入的实现类
 * @author hp
 *
 */
public class AutowiredFieldElememt extends InjectionElement {
	
	boolean require;

	public AutowiredFieldElememt(Field f,boolean required, AutowireCapableBeanFactory factory) {
		super(f, factory);
		this.require =required;
	}

	public Field getField() {
		return (Field)this.member;
	}
	
	@Override
	public void inject(Object target) {
		Field field = this.getField();
		try {
			DependencyDescriptor descriptor = new DependencyDescriptor(field, this.require);
			Object value = factory.resolveDependency(descriptor);
			if(value!=null) {
				//field.setAccessible(true);
				ReflectionUtil.makeAccessible(field);
				field.set(target, value);
			}
		}catch(Exception e) {
			throw new BeanCreationException("could not autowired field"+field);
		}
	}

}
