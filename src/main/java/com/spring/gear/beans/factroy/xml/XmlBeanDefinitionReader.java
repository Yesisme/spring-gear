package com.spring.gear.beans.factroy.xml;
/**
 * 读取xml
 * @author hp
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.spi.LoggerFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.ConstructorArgument;
import com.spring.gear.beans.factroy.BeanDifinitionStoreException;
import com.spring.gear.beans.factroy.PropertyValue;
import com.spring.gear.beans.factroy.config.RuntimeBeanReference;
import com.spring.gear.beans.factroy.config.TypedStringValue;
import com.spring.gear.beans.factroy.support.BeanDefinitionRegistry;
import com.spring.gear.beans.factroy.support.GenericBeanDefinition;
import com.spring.gear.core.io.Resource;
import com.spring.gear.utils.StringUtil;

public class XmlBeanDefinitionReader {

	protected final Log logger = LogFactory.getLog(XmlBeanDefinitionReader.class);
	
	public static final String ID_ATTRIBUTE = "id";

	public static final String CLASS_ATTRIBUTE = "class";

	public static final String SCOPE_ATTRUBUTE = "scope";

	public static final String PROPERTY_ATTRIBUTE = "property";
	
	public static final String REF_ATTRIBUTE = "ref";
	
	public static final String NAME_ATTRIBUTE = "name";
	
	public static final String VALUE_ATTRIBUTE = "value";
	
	public static final String CONTRUCTOR_ARG_ELEMENT = "constructor-arg";
	
	public static final String TYPE_ATTRIBUTE = "type";
	
	private BeanDefinitionRegistry registry;

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}

	public void loadBeanDefinition(Resource resource) {

		InputStream in = null;
		try {
			in = resource.getInputStream();
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);
			Element root = doc.getRootElement();//<beans>
			Iterator ite = root.elementIterator();
			while (ite.hasNext()) {
				Element element = (Element) ite.next();//<bean>
				String id = element.attributeValue(ID_ATTRIBUTE);
				String className = element.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition bd = new GenericBeanDefinition(id, className);
				if(element.attributeValue(SCOPE_ATTRUBUTE)!=null) {
					bd.setScope(element.attributeValue(SCOPE_ATTRUBUTE));
				}
				//解析构造函数
				parseConstructorArgElements(element, bd);
				//解析property
				parsePropertyElement(element,bd);
				this.registry.RegistryBeanDefinition(id, bd);
			}
		} catch (Exception e) {
			throw new BeanDifinitionStoreException("can not load" + resource.getDescription() + "please check it");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("io close exception");
				}
			}
		}
	}

	//取所有的property
	private void parsePropertyElement(Element beanEle, BeanDefinition bd) {
		//取到Bean标签里面所有的property标签
		Iterator ite = beanEle.elementIterator(PROPERTY_ATTRIBUTE);
		
		//如果存在继续获取property的name属性
		while(ite.hasNext()) {
			Element propertryEle = (Element) ite.next();//<propertry>
			String nameValue = propertryEle.attributeValue(NAME_ATTRIBUTE);
			//如果name值的长度为0，返回并报错
			if(!StringUtil.hasLength(nameValue)) {
				logger.fatal("Tag 'property' must have 'name' attribute:"+"这个property标签必须要用name这个属性");
				return;
			}
			//入股name的值不为空，则去解析name的值
			Object v1 = parsePropertyValue(propertryEle, bd,nameValue);
			
			PropertyValue pv = new PropertyValue(nameValue, v1);
			
			bd.getPropertiesValues().add(pv);
		}
	}
	
	//解析每个property的name的值
	private Object parsePropertyValue(Element propertryEle, BeanDefinition bd,String propertyValue) {

		//判断property标签的ref字段不为空
		boolean refField = propertryEle.attribute(REF_ATTRIBUTE)!=null;
		//判断property标签的value字段不为空
		boolean valueField = propertryEle.attribute(VALUE_ATTRIBUTE)!=null;
		//ref值存在,得到ref。判断值长度不能为0，为0报错，不为零,新建RuntimeBeanReference保存返回。
		if(refField) {
			String refFieldValue = propertryEle.attributeValue(REF_ATTRIBUTE);
			if(!StringUtil.hasText(refFieldValue)) {
				logger.error(refFieldValue +"contain empty ref value");
			}
			RuntimeBeanReference runtimeReference = new RuntimeBeanReference(refFieldValue);
			return runtimeReference;
		}else if(valueField) {
			//value值存在，得到value ，新建一个TypedStringValue返回并报存
			String valueFieldValue = propertryEle.attributeValue(VALUE_ATTRIBUTE);
			TypedStringValue typedStringValue = new TypedStringValue(valueFieldValue);
			return typedStringValue;
		}else {
			//都没有则返回报错报错,,当前只支持属性 value和ref
			throw new RuntimeException(propertyValue +"must specify a ref or value");
		}	
	}
	
	public void parseConstructorArgElements(Element element,BeanDefinition bd) {
		//判断是否有contructorarg元素
		Iterator ite = element.elementIterator(CONTRUCTOR_ARG_ELEMENT);
		//有,遍历，
		while(ite.hasNext()) {
			Element constructorArgElement =(Element) ite.next();
			parseConstrutorArgElement(constructorArgElement,bd);
		}
	}

	private void parseConstrutorArgElement(Element constructorArgElement, BeanDefinition bd) {
		//是否有type
		String typeArr = constructorArgElement.attributeValue(TYPE_ATTRIBUTE);
		//是否有name
		String nameArr = constructorArgElement.attributeValue(NAME_ATTRIBUTE);
		//通过propertyValue得到value
		Object value = parsePropertyValue(constructorArgElement, bd, null);
		//得到valueHolder
		ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
		//判断是否有有长度
		if(StringUtil.hasLength(typeArr)) {
			valueHolder.setType(typeArr);
		}
		if(StringUtil.hasLength(nameArr)) {
			valueHolder.setName(nameArr);
		}
		bd.getConstructorArgument().addArgumentValue(valueHolder);
	}
}
