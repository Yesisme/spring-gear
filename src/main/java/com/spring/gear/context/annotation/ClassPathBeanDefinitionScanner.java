package com.spring.gear.context.annotation;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.factroy.BeanDifinitionStoreException;
import com.spring.gear.beans.factroy.support.BeanDefinitionRegistry;
import com.spring.gear.beans.factroy.support.BeanNameGenerator;
import com.spring.gear.core.io.Resource;
import com.spring.gear.core.io.support.PackageResourceLoader;
import com.spring.gear.core.type.classreading.MetadataReader;
import com.spring.gear.core.type.classreading.SimpleMetaDataReader;
import com.spring.gear.stereotype.Component;
import com.spring.gear.utils.StringUtil;

public class ClassPathBeanDefinitionScanner {

	private final BeanDefinitionRegistry registry;

	private PackageResourceLoader loaderResource = new PackageResourceLoader();
	
	private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

	private final Log logger = LogFactory.getLog(getClass());

	public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}

	// 扫描包,收集和注册符合条件的
	public Set<BeanDefinition> doScan(String packageToScan) {
		// 字符串用逗号分隔变成一个数组
		String[] basePackages = StringUtil.tokenizeToStringArray(packageToScan, ",");
		// 存在这个beanDefinitions,set集合中返回
		Set<BeanDefinition> beanDefinitions = new LinkedHashSet<BeanDefinition>();
		for (String basePackage : basePackages) {
			Set<BeanDefinition> candidateSet = findCandidateComponents(basePackage);
			for (BeanDefinition bd : candidateSet) {
				beanDefinitions.add(bd);
				this.registry.RegistryBeanDefinition(bd.getId(), bd);
			}
		}
		return beanDefinitions;
	}

	// 扫描每个包,寻找符合条件的注解
	private Set<BeanDefinition> findCandidateComponents(String basePackage) {
		//新建一个要返回的集合
		Set<BeanDefinition> candidatas = new LinkedHashSet<BeanDefinition>();
		try {
			Resource[] resources = this.loaderResource.getResource(basePackage);
			
			for (Resource resource : resources) {
			try {
				 MetadataReader metaDataReader = new SimpleMetaDataReader(resource);
				 if(metaDataReader.getAnotationMetadata().hasAnnotation(Component.class.getName())) {
					 ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metaDataReader.getAnotationMetadata());
					 String beanName = this.beanNameGenerator.generateBeanName(sbd,this.registry);
					 sbd.setId(beanName);
					 candidatas.add(sbd);
				 }
				}catch(Throwable ex) {
					throw new BeanDifinitionStoreException("Faild to reader candidate componetn class "+resource, ex);
				}
			}
		}catch(Throwable ex) {
			throw new BeanDifinitionStoreException("I/o failed to during classpath scanner", ex);	
		}
		return candidatas;
	}
}
