package com.spring.gear.service.v2;

import com.spring.gear.dao.v2.AccuntDao;
import com.spring.gear.dao.v2.ItemDao;

public class ZooService {

	private AccuntDao accuntDao;
	
	private ItemDao itemDao;
	
	private String owner;
	
	private int version;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public AccuntDao getAccuntDao() {
		return accuntDao;
	}

	public void setAccuntDao(AccuntDao accuntDao) {
		this.accuntDao = accuntDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

}
