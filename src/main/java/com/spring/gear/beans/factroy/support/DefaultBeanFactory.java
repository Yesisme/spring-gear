package com.spring.gear.beans.factroy.support;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.factroy.BeanDifinitionStoreException;
import com.spring.gear.beans.factroy.BeanFactory;
import com.spring.gear.utils.ClassUtil;

public class DefaultBeanFactory implements BeanFactory{

	private static final String ID_ATTRIBUTE = "id";
	private static final String CLASS_ATTRIBUTE = "class";
	
	private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
 
	public DefaultBeanFactory(String configFileName) {
		loadBeanDefinition(configFileName);
	}

	//解析配置文件
	private void loadBeanDefinition(String configFileName) {
		InputStream in =null;
		try {
			ClassLoader cl = ClassUtil.getDefaultClassLoader();
			in = cl.getResourceAsStream(configFileName);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);
			Element rootElement = doc.getRootElement();//得到root节点 //beans
			Iterator ite = rootElement.elementIterator();
			while (ite.hasNext()) {
				Element element = (Element) ite.next();
				String id = element.attributeValue(ID_ATTRIBUTE);
				String className = element.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition bd = new GenericBeanDefinition(id, className);
				this.beanDefinitionMap.put(id, bd);
			}
		}catch(DocumentException e) {
			throw new BeanDifinitionStoreException(configFileName +"dose not exits");
		}finally {
			if(in!=null) {
				try {
					in.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Object getBean(String BeanId) {
		//通过BeanId得到BeanDefinition
		BeanDefinition beanDefinition = this.beanDefinitionMap.get(BeanId);
		if(beanDefinition==null) {
			throw new BeanCreationException("Bean Definition does not exits");
		}
		//通过ClassUtil加载className
		ClassLoader defaultClassLoader = ClassUtil.getDefaultClassLoader();
		//通过BeanDefinition得到ClassName
		String beanClassName = beanDefinition.getBeanClassName();
		try {
			Class<?> loadClass = defaultClassLoader.loadClass(beanClassName);
			//反射newInstance得到实例
			return loadClass.newInstance();
		} catch (Exception e) {
			throw new BeanCreationException("Bean Definition" +beanClassName+"does not exits");
		} 
	}

	@Override
	public BeanDefinition getBeanDefinition(String BeanId) {
		
		return this.beanDefinitionMap.get(BeanId);
	}

}