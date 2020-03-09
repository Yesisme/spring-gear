package com.spring.gear.test.v5;

import org.junit.Test;

import com.spring.gear.aop.aspectj.AspectjExpressionPointCut;

public class PointCutTest {

	@Test
	public void testPointCut() {
		String expression = "execution(* com.spring.service.v5.*.placeOrder(..))";
		AspectjExpressionPointCut aspect = new AspectjExpressionPointCut();
		aspect.setExpression(expression);
		
		{
			
		}
	}
}
