package com.spring.gear.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class ClassUtil {
	
	public static final char PATH_SEPARATOR = '/';
	
	public static final char PACKAGE_SEPARATOR = '.';
	
	private static final char INNER_CLASS_SEPARATOR = '$';

    public static final String CGLIB_CLASS_SEPARATOR = "$$";

	private static final Map<Class<?>, Class<?>> wrapperToPrimitiveTypeMap = new HashMap<Class<?>, Class<?>>(8);

	private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new HashMap<Class<?>, Class<?>>(8);

	static {
		wrapperToPrimitiveTypeMap.put(Boolean.class, boolean.class);
		wrapperToPrimitiveTypeMap.put(Byte.class, byte.class);
		wrapperToPrimitiveTypeMap.put(Character.class, char.class);
		wrapperToPrimitiveTypeMap.put(Double.class, double.class);
		wrapperToPrimitiveTypeMap.put(Float.class, float.class);
		wrapperToPrimitiveTypeMap.put(Integer.class, int.class);
		wrapperToPrimitiveTypeMap.put(Long.class, long.class);
		wrapperToPrimitiveTypeMap.put(Short.class, short.class);

		for (Map.Entry<Class<?>, Class<?>> classClassEntry : wrapperToPrimitiveTypeMap.entrySet()) {
			primitiveTypeToWrapperMap.put(classClassEntry.getValue(), classClassEntry.getKey());
		}
	}

	
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;

		// 获取
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Exception e) {

		}

		if (cl == null) {
			cl = ClassUtil.class.getClassLoader();

			if (cl == null) {
				try {
					cl = ClassLoader.getSystemClassLoader();
				} catch (Exception e) {

				}
			}
		}
		return cl;
	}

	public static boolean isAssignableValue(Class<?> type, Object value) {
		Assert.notNull(type, "Type must not be null");
		return (value != null ? isAssignable(type, value.getClass()) : !type.isPrimitive());

	}

	public static boolean isAssignable(Class<?> lhsType, Class<?> rhsType) {
		Assert.notNull(lhsType, "Left-hand side type must not be null");
		Assert.notNull(rhsType, "Right-hand side type must not be null");

		if (lhsType.isAssignableFrom(rhsType)) {
			return true;
		}
		// 判断Class是否为原始类型
		if (lhsType.isPrimitive()) {
			Class<?> resolvedPrimitive = wrapperToPrimitiveTypeMap.get(rhsType);
			if (resolvedPrimitive != null && lhsType.equals(resolvedPrimitive)) {
				return true;
			}
		} else {
			Class<?> resolvedWrapper = primitiveTypeToWrapperMap.get(rhsType);
			if (resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper)) {
				return true;
			}

		}
		return false;
	}

	public static String convertClassPathtoResourcePath(String basePackage) {
		Assert.notNull(basePackage,"Class name must not be null");
		return basePackage.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
	}
	
	public static String convertResourcePathToClassPath(String basePackage) {
		Assert.notNull(basePackage,"Class name must not be null");
		return basePackage.replace(PATH_SEPARATOR, PACKAGE_SEPARATOR);
	}

	public static String getShortName(String className) {

        int lastDotIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
        int nameEndIndex = className.indexOf(CGLIB_CLASS_SEPARATOR);
        if (nameEndIndex == -1) {
            nameEndIndex = className.length();
        }
        String shortName = className.substring(lastDotIndex + 1, nameEndIndex);
        shortName = shortName.replace(INNER_CLASS_SEPARATOR, PACKAGE_SEPARATOR);
        return shortName;
    }
	
}
