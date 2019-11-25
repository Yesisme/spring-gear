package com.spring.gear.test.v4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.spring.gear.core.io.Resource;
import com.spring.gear.core.io.support.PackageResourceLoader;
//第一步
public class PackageResourceLoaderTest {

	@Test
	public void testPackageResourceLoader() {
		PackageResourceLoader loader = new PackageResourceLoader();
		Resource[] resource = loader.getResource("com.spring.gear.dao.v4");
		assertEquals(2, resource.length);
	}
	
}
