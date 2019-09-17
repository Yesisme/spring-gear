package com.spring.gear.beans.factroy;

import com.spring.gear.beans.BeansException;

/**
 * 读取xml配置文件出错抛出此异常
 * @author hp
 *
 */
public class BeanDifinitionStoreException extends BeansException{

	public BeanDifinitionStoreException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
	public BeanDifinitionStoreException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
