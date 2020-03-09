package com.spring.gear.core.io.support;


import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.spring.gear.core.io.FileSystemResource;
import com.spring.gear.core.io.Resource;
import com.spring.gear.utils.Assert;
import com.spring.gear.utils.ClassUtil;
/**
 * 扫描包得到Resource实例 
 * @author hp
 *
 */
public class PackageResourceLoader {
	
	private static final Log logger = LogFactory.getLog(PackageResourceLoader.class);
	
	private final ClassLoader classLoader; 
	
	public PackageResourceLoader() {
		this.classLoader = ClassUtil.getDefaultClassLoader();
	}
	
	public PackageResourceLoader (ClassLoader classLoader) {
		Assert.notNull(classLoader, "ResourceLoader must not be null");
		this.classLoader = classLoader;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public Resource[] getResource(String basePackage) {
		//将class的路径. 变成包的路径斜杠 location
		String location = ClassUtil.convertClassPathtoResourcePath(basePackage);
		//得到classloader cl,并通过cl的方法获取url
		ClassLoader cl = getClassLoader();
		//得到在磁盘中的路径
		URL url = cl.getResource(location);
		//得到File的根目录 rootFile 去除File得到全路径
		File rootFile = new File (url.getFile());
		//将rootFile传入 retrieveMatchingFiles方法中,返回一个matchingFiles 
		Set<File> matchingFiles = retrieveMatchingFiles(rootFile);
		//new 一个Reource保存所有的matchFile
		Resource[] result = new Resource[matchingFiles.size()];

		int i= 0;
		for (File file : matchingFiles) {
			result[i++] = new FileSystemResource(file);
		}
		return result;
	}
	
	public Set<File> retrieveMatchingFiles(File rootFile){
		
		//判断是否存在
		Assert.notNull(rootFile, "rootFile cannot be null");
		
		//判断是否是目录
		if(!rootFile.isDirectory()) {
			logger.isWarnEnabled();
			logger.info("the rootFile cannot be a Directroy");

			 return Collections.emptySet();
		}
		
		//判读是否可读
		if(!rootFile.canRead()) {
			logger.isWarnEnabled();
			logger.info("the rootFile cannot be reader");
			
			 return Collections.emptySet();
		}
		
		//通过 则new一个set<File> result,并将rootFile 和result传入doRetrieveMatchingFiles方法中
		Set<File> result = new LinkedHashSet<>(8);
		
		doRetrieveMatchingFiles(rootFile, result);
		//返回这个result
		return result;
	}
	
	public void doRetrieveMatchingFiles(File dir,Set<File> set) {
		
		//得到目录下的所有文件
		File[] listFiles = dir.listFiles();
		if(listFiles==null) {
			 if (logger.isWarnEnabled()) {
	                logger.warn("Could not retrieve contents of directory [" + dir.getAbsolutePath() + "]");
	            }
	            return;
		}
		//不等于 for循环遍历
		for (File file : listFiles) {
			if(file.isDirectory()) {
				if(!file.canRead()) {
					logger.info(file +"the file cannot be reader");
				}
				//判断是否是目录,不是目录则添加到result,判断是否可读，不可读则报错，可读则继续调用doRetrieveMathcingFiles方法
				doRetrieveMatchingFiles(file, set);
			}else {
				set.add(file);
			}
		}
	}
}
