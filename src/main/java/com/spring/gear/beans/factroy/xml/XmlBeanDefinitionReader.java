package com.spring.gear.beans.factroy.xml;
/**
 * 读取xml
 * @author hp
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.factroy.BeanDifinitionStoreException;
import com.spring.gear.beans.factroy.support.BeanDefinitionRegistry;
import com.spring.gear.beans.factroy.support.GenericBeanDefinition;
import com.spring.gear.utils.ClassUtil;

public class XmlBeanDefinitionReader {
	
	private static final String ID_ATTRIBUTE = "id";
	
	private static final String CLASS_ATTRIBUTE = "class";

	private BeanDefinitionRegistry registry;
	
	public XmlBeanDefinitionReader (BeanDefinitionRegistry registry) {
		this.registry = registry;
	}
	
	public void loadBeanDefinition(String configFileName) {
		
		InputStream in = null;
		try {
			ClassLoader cl = ClassUtil.getDefaultClassLoader();
			in = cl.getResourceAsStream(configFileName);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			Iterator ite = root.elementIterator();
			while(ite.hasNext()) {
				Element element = (Element)ite.next();
				String id = element.attributeValue(ID_ATTRIBUTE);
				String className = element.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition bd = new GenericBeanDefinition(id, className);
				this.registry.RegistryBeanDefinition(id, bd);
			}
		} catch (DocumentException e) {
			throw new BeanDifinitionStoreException("can not load" + configFileName +"please check it");
		}finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("io close exception");
				}
			}
		}
	}
	
}
