package com.spring.gear.service.v3;

import com.spring.gear.dao.v3.AccuntDao;
import com.spring.gear.dao.v3.ItemDao;

public class ZooService {

	private AccuntDao accuntDao;
	
	private ItemDao itemDao;
	
	private int version;
	
	public ZooService(AccuntDao accuntDao, ItemDao itemDao) {
		super();
		this.accuntDao = accuntDao;
		this.itemDao = itemDao;
		this.version = 0;
	}

	public ZooService(AccuntDao accuntDao, ItemDao itemDao, int version) {
		super();
		this.accuntDao = accuntDao;
		this.itemDao = itemDao;
		this.version = version;
	}

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
