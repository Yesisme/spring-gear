package com.spring.gear.aop;

public interface PointCut {

	MethodMatcher getMethodMatcher();
	
	String getExpression();
}
