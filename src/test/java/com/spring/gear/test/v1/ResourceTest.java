package com.spring.gear.test.v1;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.spring.gear.core.io.ClassPathResource;
import com.spring.gear.core.io.FileSystemResource;
import com.spring.gear.core.io.Resource;

public class ResourceTest {

	@Test
	public void testClassPathResource() throws FileNotFoundException {
		InputStream is = null;
		Resource r = new ClassPathResource("zoo-v1.xml");
		try {
			is = r.getInputStream();
			Assert.assertNotNull(is);
		}finally{
			if(is!=null) {
				try {
					is.close();
				}catch(Exception e) {
					
				}
			}
		}
	}
	
	@Test
	public void testFileSystemResource() throws Exception {
		InputStream in = null;
		Resource r = new FileSystemResource("E:\\spring-gear\\zoo-v1.xml");
		try {
			in = r.getInputStream();
			Assert.assertNotNull(in);
		}finally {
			in.close();
		}
	}
}
