package com.spring.gear.tx;

import com.spring.gear.util.MessageTracker;

public class TransactionManager {

	public void start() {
		System.out.println("start tx");
		MessageTracker.setMessage("start tx");
	}
	
	public void commit() {
		System.out.println("commit tx");
		MessageTracker.setMessage("commit tx");
	}
	
	public void rollback() {
		System.out.println("rollback tx");
		MessageTracker.setMessage("rollback tx");
	}
}
