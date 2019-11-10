package com.spring.gear.beans.factroy.annotation;

import java.util.List;

public class InjectionMetadata {

	private final Class<?> targetClass;
	
	private List<InjectionElement> injectionElements;
	
	public InjectionMetadata(Class<?> targetClass,List<InjectionElement> injectionElements) {
		this.targetClass = targetClass;
		this.injectionElements = injectionElements;
	}
	
	public List<InjectionElement> getInjectionElement(){
		return this.injectionElements;
	}
	
	public void inject(Object target) {
		if(injectionElements == null || injectionElements.isEmpty()) {
			return;
		}
	
		for (InjectionElement ele : injectionElements) {
			ele.inject(target);
		}
	}
}
