package com.spring.gear.beans.factroy.support;

import com.spring.gear.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition{
	
	private String id;
	
	private String className;
	
	private boolean singleton = true;
	
	private boolean propotype = false;
	
	private String scope = SCOPE_DEFAULT;
	
	public GenericBeanDefinition(String id,String className) {
		this.id = id;
		this.className = className;
	}
	@Override
	public String getBeanClassName() {
		
		return this.className;
	}
	@Override
	public boolean isSingleton() {
		
		return this.singleton;
	}
	@Override
	public boolean isPropotype() {
	
		return this.propotype;
	}
	@Override
	public String getScope() {
	
		return this.scope;
	}
	@Override
	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
		this.propotype = SCOPE_PROTOTYPE.equals(scope);
	}

}
