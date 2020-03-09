package com.spring.gear.aop;

import org.aspectj.apache.bcel.classfile.Method;

public interface MethodMatcher {

	boolean matches(Method method1);
}
