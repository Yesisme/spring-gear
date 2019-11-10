package com.spring.gear.context.support;

import com.spring.gear.core.io.ClassPathResource;
import com.spring.gear.core.io.Resource;

/**
 * 继承抽象类AbstractApplicationContext,使用模板方法解决重复代码的问题
 * @author hp
 *
 */
public class ClassPathXmlApplcationContext extends AbstractApplicationContext{

	public ClassPathXmlApplcationContext(String configFlie) {
		super(configFlie);
	}

	@Override
	protected Resource getResourceByPath(String path) {
		
		return new ClassPathResource(path,this.getClassLoader());
	}

	
	/*private DefaultBeanFactory factory = null;
	
	private Resource resource = null;
	
	public ClassPathXmlApplcationContext(String configFileName) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		//选择不同的方式去得到流
		resource = new ClassPathResource(configFileName);
		reader.loadBeanDefinition(configFileName);
	}
	
	@Override
	public Object getBean(String BeanId) {
		
		return factory.getBean(BeanId);
	}*/




}
