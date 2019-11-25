package com.spring.gear.beans.factroy.support;

import java.lang.reflect.Constructor;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.spring.gear.beans.BeanDefinition;
import com.spring.gear.beans.ConstructorArgument;
import com.spring.gear.beans.ConstructorArgument.ValueHolder;
import com.spring.gear.beans.SimpleTypeCoverter;
import com.spring.gear.beans.factroy.BeanFactory;
import com.spring.gear.utils.ClassUtil;

public class ConstrutorResovler {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private final BeanFactory beanFactory;
	
	public ConstrutorResovler(BeanFactory beanFactory) {
		this.beanFactory =  beanFactory;
	}
	
	public Object autowireConstructor(final BeanDefinition bd) {
		//新建一个null的ConstructorToUse
		Constructor<?> constructorToUse = null;
		//新建一个空的数组argToUse
		Object[] argToUse = null;
		//定义一个任意类型的空的beanClass
		Class<?> beanClass = null;
		//通过bd的方法得到一个class，并加载它，执行失败抛出 Instancetiation of bean failed cannot resolver calss
		try {
			beanClass = ClassUtil.getDefaultClassLoader().loadClass(bd.getBeanClassName());
		}catch(Exception e) {
			throw new BeanCreationException("Instancetiation of bean failed cannot resolver calss");
		}
		//通过calss对象反射得到所有的构造方法 condidates
		Constructor<?>[] constructors = beanClass.getConstructors();
		//new一个BeanDefinitionValue 传入当前的factory
		BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this.beanFactory);
		//通过bd得到所有的argment 
		ConstructorArgument args = bd.getConstructorArgument();
		//new一个SimpleTypeConvert
		SimpleTypeCoverter typeConvert = new SimpleTypeCoverter();
		//循环遍历所有的构造方法，获取每个方法的参数，如果构造方法中的参数个数不是arg的个数
		for (int i=0;i<constructors.length;i++) {
			//获取构造方法中的个人
			Class<?>[] parameterTypes = constructors[i].getParameterTypes();
			if(parameterTypes.length!=args.getArgumentCount()) {
				//跳出当前循环
				continue;
			}
			//argToUse实例化 传入parametetype的长度
			argToUse = new Object[parameterTypes.length];
			
			boolean result = this.valuesMatchTypes(parameterTypes,args.getArgumentValues(),argToUse,valueResolver,typeConvert);
			
			//匹配ConstructorToUse赋值并跳出
			if(result) {
				constructorToUse = constructors[i];
			}
		}
		//循环完没有匹配抛出一个 
		if(constructorToUse == null) {
			throw new BeanCreationException(bd.getId()+"cannot find a apporiate construtor");
		}
		
		//否则通过反射ConstructorToUse创建对象返回
		try {
			return constructorToUse.newInstance(argToUse);
		}catch(Exception e) {
			throw new BeanCreationException("cannot find instance use");
		}
	}
	

	
	private boolean valuesMatchTypes(Class<?>[] parameterTypes,List<ConstructorArgument.ValueHolder> valuesHolders,Object[] argsToUse,BeanDefinitionValueResolver valueResolver,SimpleTypeCoverter typeCoverter) {
		//循环parameterTypes，得到valueHolder。可能是TypedStringValue,也可能是RuntimeBeanReference
		for(int i=0;i<parameterTypes.length;i++) {
			//获取真正的值，
			ConstructorArgument.ValueHolder valueHolder = valuesHolders.get(i);
			
			Object originalVlaue = valueHolder.getValue();
			
			try {
				//获取真正的值
				Object resolverValue = valueResolver.resolverValueIfNecessary(originalVlaue);	
				//对int类型转换
				Object convertValue = typeCoverter.convertIfNecessary(resolverValue, parameterTypes[i]);
				//是则记录到argsToUser
				argsToUse[i] = convertValue;
			}catch(Exception e) {
				logger.error(e);
				return false;
			}
			
		}
			return true;
	}
	
}
