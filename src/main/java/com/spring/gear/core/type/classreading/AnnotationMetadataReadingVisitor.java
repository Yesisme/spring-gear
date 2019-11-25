package com.spring.gear.core.type.classreading;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Type;

import com.spring.gear.core.annotation.AnnotationAttributes;
import com.spring.gear.core.type.AnnotationMetadata;
/**
 * 专门用来处理注解的方法,同时具备了vistor和visitAnnotation
 * @author hp
 *
 */
public class AnnotationMetadataReadingVisitor extends ClassMetaReaderingVisitor implements AnnotationMetadata{

	private Set<String> annotationSet = new LinkedHashSet<String>(4);
	
	private Map<String,AnnotationAttributes> attributeMap = new LinkedHashMap<String,AnnotationAttributes>(4);
	
	public AnnotationMetadataReadingVisitor() {
		
	}
	
	//vistor方法调用完成后调用此方法，得到注解名
	@Override
	public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
		//通过desc获取className
		String className = Type.getType(desc).getClassName();
		//添加到当前annotationset当中
		this.annotationSet.add(className);
		//返回一个对象,AnnotationAttributesReadingVisitor的vistor方法被调用
		return new AnnotationAttributesReadingVisitor(className,this.attributeMap);
	}

	public Set<String> getAnnotationTypes(){
		return this.annotationSet;
	}
	
	public boolean hasAnnotation(String annotationType) {
		return this.annotationSet.contains(annotationType);
	}
	
    public AnnotationAttributes getAnnotationAttributes(String annotationType) {
        return this.attributeMap.get(annotationType);
    }

    public boolean hasSuperClass() {
        return false;
    }
}
