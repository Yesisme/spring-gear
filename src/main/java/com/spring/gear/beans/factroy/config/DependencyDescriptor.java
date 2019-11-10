package com.spring.gear.beans.factroy.config;

import java.lang.reflect.Field;

import com.spring.gear.utils.Assert;

public class DependencyDescriptor {

	private Field field;
	
	private boolean required;
	
	public DependencyDescriptor(Field field,boolean required) {
		Assert.notNull(field, "field cannot be null");
		this.field = field;
		this.required = required;
	}

	public Class<?> getFieldType() {
		if(this.field!=null) {
			return this.field.getType();
		}
		throw new RuntimeException("only support filed dependency");
	}

	public boolean isRequired() {
		return required;
	}
}
