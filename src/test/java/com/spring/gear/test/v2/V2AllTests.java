package com.spring.gear.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ApplicationContextV2.class, BeanDefinitionTestV2.class, BeanDefinitionValueResolveTest.class, CustomBooleamEditorTest.class, CustomNumberEditorTest.class, TypeConverterTest.class })
public class V2AllTests {

}
