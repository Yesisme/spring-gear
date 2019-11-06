package com.spring.gear.context;

import com.spring.gear.beans.factroy.config.ConfigurableBeanFactory;
/**
 * 意义：只暴露出最小的接口，setClassLoader getClassLoader getBean
 * @author hp
 *
 */
public interface ApplicationContext extends ConfigurableBeanFactory{


} 
