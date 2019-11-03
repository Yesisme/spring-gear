package com.spring.gear.beans.factroy;

public class PropertyValue {

	private final String name;
	
	private final Object value;
	
	private boolean coverted = false;
	
	private Object covertedValue;
	
	public PropertyValue(String name,Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Object getValue() {
		return this.value;
	}

	public synchronized boolean isCoverted() {
		return this.coverted;
	}
  
	public void setCoverted(boolean coverted) {
		this.coverted = coverted;
	}

	public synchronized Object getCovertedValue() {
		return this.covertedValue;
	}

	public synchronized void setCovertedValue(Object covertedValue) {
		this.coverted = true;
		this.covertedValue = covertedValue;
	}
}
