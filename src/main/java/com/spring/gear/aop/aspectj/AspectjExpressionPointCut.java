package com.spring.gear.aop.aspectj;

import java.util.HashSet;
import java.util.Set;

import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.weaver.tools.PointcutPrimitive;

import com.spring.gear.aop.MethodMatcher;
import com.spring.gear.aop.PointCut;

public class AspectjExpressionPointCut implements PointCut,MethodMatcher{

	private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }
    
    private String expression;
    
	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public boolean matches(Method method1) {
		
		return false;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		
		return this;
	}

	@Override
	public String getExpression() {
		
		return null;
	}

	
}
