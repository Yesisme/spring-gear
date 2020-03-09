package com.spring.gear.service.v5;

import com.spring.gear.beans.factroy.annotation.Autowired;
import com.spring.gear.dao.v5.AccountDao;
import com.spring.gear.dao.v5.ItemDao;
import com.spring.gear.stereotype.Component;
import com.spring.gear.util.MessageTracker;

@Component("zooService")
public class ZooService {

	 @Autowired
	 private AccountDao accountDao;
	 
	 @Autowired
	 private ItemDao itemDao;

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}
	

	public void placeOrder() {
		System.out.println("place order");
		MessageTracker.setMessage("place order");
	}
	
	public void placeOrderWithException() {
		throw new NullPointerException();
	}
 
	
}
