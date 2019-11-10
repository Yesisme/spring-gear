package com.spring.gear.service.v4;

import com.spring.gear.beans.factroy.annotation.Autowired;
import com.spring.gear.dao.v4.AccountDao;
import com.spring.gear.dao.v4.ItemDao;
import com.spring.gear.stereotype.Component;

@Component(value="zooService")
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
	
	
}
