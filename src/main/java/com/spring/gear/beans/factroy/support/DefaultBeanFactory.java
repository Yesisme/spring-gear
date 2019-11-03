package com.spring.gear.beans.factroy.support;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.SimpleTypeCoverter;
import com.spring.gear.beans.factroy.BeanFactory;
import com.spring.gear.beans.factroy.PropertyValue;
import com.spring.gear.beans.factroy.config.ConfigurableBeanFactory;
import com.spring.gear.utils.ClassUtil;

/**
 * 继承BeanDefinitionRegistry,XmlBeanDefinition
 * @author hp
 * 
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory,BeanDefinitionRegistry{
	
	private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap(64);
	
	private ClassLoader classLoader;
	
	@Override
	public void RegistryBeanDefinition(String BeanId, BeanDefinition beanDefinition) {
		this.beanDefinitionMap.put(BeanId, beanDefinition);
	}

	@Override
	public Object getBean(String beanId) {
		BeanDefinition bd = this.beanDefinitionMap.get(beanId);
		//如果bd是单例的
		if(bd.isSingleton()) {
			//得到这个对象
			Object bean = this.getSingleton(beanId);
			//如果这个对象是空
			if(bean==null) {
				//通过反射创建这个Bean
				bean = createBean(bd);
				//然后注册这个单例的
				this.registrySingleton(beanId, bean);
				
			}
			//返回这个Bean
			return bean;
		}
		return createBean(bd);
	}
	
	private Object createBean(BeanDefinition bd) {
		//初始化bean
		Object bean = instantiateBean(bd);
		
		//设置属性
		populateBean(bd,bean);
		
		return bean;
		
	}
	
	private Object instantiateBean(BeanDefinition bd) {
		ClassLoader cl = ClassUtil.getDefaultClassLoader();
		String className = bd.getBeanClassName();
		try {
			Class<?> alass = cl.loadClass(className);
			return alass.newInstance();
		} catch (Exception e) {
			throw new BeanCreationException("create Bean" + className +"fail");
		}
	}
	
	private void populateBean(BeanDefinition bd, Object bean) {
		//得到xml的bean标签中所有property的值
		List<PropertyValue> propertiesValues = bd.getPropertiesValues();
		//判断是否为空 ,为空则返回
		if(propertiesValues==null || propertiesValues.isEmpty()) {
			return;
		}
		//实例化解析器 BeanDefinitionValueResovler
		BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
		//for循环所有的property,得到proprtyName(property标签的name的值),得到oiginValue(实例),
		SimpleTypeCoverter converter = new SimpleTypeCoverter();
		try {
			for (PropertyValue pv : propertiesValues) {
				String propertyName = pv.getName();
				Object originValue = pv.getValue();
				//通过解析器解析得到一个resolvedValue
				Object resolverValue = resolver.resolverValueIfNecessary(originValue);
				//通过缺省api的bean的class 得到这个bean的信息，并获取所有的属性，
				BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
				//for循环 ，如果这个bean的信息中等于这(property标签的name的值，则调用缺省api的set方法注入，跳出
				 PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				 for (PropertyDescriptor pd : propertyDescriptors) {
					if(pd.getName().equals(propertyName)) {
						Object converedValue = converter.convertIfNecessary(resolverValue, pd.getPropertyType());
						pd.getWriteMethod().invoke(bean, converedValue);
						break;
					}
				}
			}
		}catch(Exception e) {
			throw new BeanCreationException("failed to obtain"+bd.getClass());
		}
	}

	@Override
	public BeanDefinition getBeanDefinition(String BeanId) {
		
		return this.beanDefinitionMap.get(BeanId);
	}

	@Override
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public ClassLoader getClassLoader() {
		return this.classLoader;
	}

	//BeanFactory上半部分
	/*private static final String ID_ATTRIBUTE = "id";
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
	}*/

}
