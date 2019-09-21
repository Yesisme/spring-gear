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
	}
	
	public BeanDifinitionStoreException(String message) {
		super(message);
	}
}
