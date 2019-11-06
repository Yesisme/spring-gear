package com.spring.gear.service.v3;

import com.spring.gear.dao.v3.AccuntDao;
import com.spring.gear.dao.v3.ItemDao;

public class ZooService {

	private AccuntDao accuntDao;
	
	private ItemDao itemDao;
	
	private int version;

	public AccuntDao getAccuntDao() {
		return accuntDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public int getVersion() {
		return version;
	}
}
