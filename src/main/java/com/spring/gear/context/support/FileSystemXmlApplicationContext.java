package com.spring.gear.context.support;

import com.spring.gear.core.io.FileSystemResource;
import com.spring.gear.core.io.Resource;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext{
	
	public FileSystemXmlApplicationContext(String configFlie) {
		super(configFlie);
	}

	@Override
	protected Resource getResourceByPath(String configFlie) {
		
		return new FileSystemResource(configFlie);
	}
}
