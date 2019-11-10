package com.spring.gear.core.annotation;

import java.util.LinkedHashMap;
import java.util.Map;

import com.spring.gear.utils.Assert;

/**
 * 对LinkedHashMap进行的扩展,本质就是一个map,用来存放注解的一些信息
 * @author hp
 *
 */
@SuppressWarnings("serial")
public class AnnotationAttributes extends LinkedHashMap<String,Object>{

	public AnnotationAttributes() {}
	
	public AnnotationAttributes(int initialCapacity) {
		super(initialCapacity);
	}
	
	public AnnotationAttributes(Map<String,Object> map) {
		super(map);
	}
	
	public String getString(String attributeName) {
		return doGet(attributeName,String.class);
	}
	
	public String[] getStringAttributeName(String attributeName) {
		return doGet(attributeName,String[].class);
	}
	
	public boolean getBoolean(String attributeName) {
		return doGet(attributeName,Boolean.class);
	}

	public <N extends Number> N getNumber(String attributeName) {
        return (N) doGet(attributeName, Integer.class);
    }

    public <E extends Enum<?>> E getEnum(String attributeName) {
        return (E) doGet(attributeName, Enum.class);
    }

    public <T> Class<? extends T> getClass(String attributeName) {
        return doGet(attributeName, Class.class);
    }

    public Class<?>[] getClassArray(String attributeName) {
        return doGet(attributeName, Class[].class);
    }
	
	public <T> T doGet(String attributeName,Class<T> expectedType) {
		Object value = this.get(attributeName);
		Assert.notNull(value, String.format("Attribute '%s' not found", attributeName));
		return (T)value;
	}
}
