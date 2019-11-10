package com.spring.gear.core.type.classreading;

import java.util.Map;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;

import com.spring.gear.core.annotation.AnnotationAttributes;
/**
 * 对注解属性进行解析
 * @author hp
 *
 */
public final class AnnotationAttributesReadingVisitor extends AnnotationVisitor {
	
	private final String annotationType;
	
	private final Map<String,AnnotationAttributes> attributesMap;
	
	AnnotationAttributes attributes = new AnnotationAttributes();
	
	public AnnotationAttributesReadingVisitor(String annotationType, Map<String, AnnotationAttributes> attributesMap) {
		super(SpringAsmInfo.ASM_VERSION);
		this.annotationType = annotationType;
		this.attributesMap = attributesMap;
	}
	
	@Override
    public final void visitEnd() {
        this.attributesMap.put(this.annotationType, this.attributes);
    }

    @Override
    public void visit(String attributeName, Object attributeValue) {
        this.attributes.put(attributeName, attributeValue);
    }
}
