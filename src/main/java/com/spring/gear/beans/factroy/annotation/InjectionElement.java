package com.spring.gear.beans.factroy.annotation;

import java.lang.reflect.Member;

import com.spring.gear.beans.factroy.config.AutowireCapableBeanFactory;

public abstract class InjectionElement {

	protected Member member;
	protected AutowireCapableBeanFactory factory;
	
	InjectionElement(Member member,AutowireCapableBeanFactory factory){
		this.member =member;
		this.factory =factory;
	}
	
	public abstract void inject(Object target);
}
