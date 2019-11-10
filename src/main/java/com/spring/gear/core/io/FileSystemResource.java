package com.spring.gear.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.spring.gear.utils.Assert;

public class FileSystemResource implements Resource{
	
	private final String path;
	
	private final File file;
	
	public FileSystemResource(File file ) {
		this.path = file.getPath();
		this.file = file;
	}
	
	public FileSystemResource(String path) {
		Assert.notNull(path, "path cannot be null");
		this.file = new File(path);
		this.path = path;
	}

	@Override
	public InputStream getInputStream() throws FileNotFoundException {
		
		 return new FileInputStream(this.file);
	}

	@Override
	public String getDescription() {
		
		return "File"+"[" + this.file.getAbsolutePath() +"]";
	}



}
