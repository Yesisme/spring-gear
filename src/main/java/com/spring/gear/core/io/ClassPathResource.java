package com.spring.gear.core.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.spring.gear.utils.ClassUtil;

public class ClassPathResource implements Resource{
	
	private String path;
	private ClassLoader classLoader;
	
	public ClassPathResource(String path) {
		this(path,(ClassLoader)null);
	}
	
	public ClassPathResource(String path,ClassLoader classLoader) {
		this.path = path;
		this.classLoader= (classLoader!=null?classLoader:ClassUtil.getDefaultClassLoader());
	}
	
	@Override
	public InputStream getInputStream() throws FileNotFoundException {
		
		InputStream in = this.classLoader.getResourceAsStream(this.path);
		if(in==null) {
			throw new FileNotFoundException(this.path+"not found");
		}
		return in;
	}
	@Override
	public String getDescription() {

		return this.path;
	}

}
