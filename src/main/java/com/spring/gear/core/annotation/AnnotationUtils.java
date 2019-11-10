package com.spring.gear.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public class AnnotationUtils {

	public static<T extends Annotation> T getAnnotation(AnnotatedElement ae,Class<T> annotattionType) {
		T ann = ae.getAnnotation(annotattionType);
		if(ann==null) {
			for (Annotation metaAnn : ae.getAnnotations()) {
				ann = metaAnn.annotationType().getAnnotation(annotattionType);
				if(ann !=null) {
					break;
				}
			}
		}
		return ann;
	}
}
