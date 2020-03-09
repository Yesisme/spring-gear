package com.spring.gear.core.type.classreading;

import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.asm.SpringAsmInfo;

import com.spring.gear.core.type.ClassMetadata;
import com.spring.gear.utils.ClassUtil;

public class ClassMetaReaderingVisitor extends ClassVisitor implements ClassMetadata{

	private String className;
	
	private boolean isAbstract;
	
	private boolean isInterface;
	
	private boolean isFinal;
	
	private String superClassName;
	
	private String[] interfaces;
	
	public ClassMetaReaderingVisitor() {
		super(SpringAsmInfo.ASM_VERSION);
	}
	
	public ClassMetaReaderingVisitor(int i) {
		super(i);
	}
	
	public ClassMetaReaderingVisitor(int i,ClassVisitor classVistitor) {
		super(i,classVistitor);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String supername, String[] interfaces) {
		this.className = ClassUtil.convertResourcePathToClassPath(name);
		this.isInterface = ((access & Opcodes.ACC_INTERFACE)!=0);
		this.isAbstract = ((access & Opcodes.ACC_ABSTRACT)!=0);
		this.isFinal = ((access & Opcodes.ACC_FINAL)!=0);
		if(supername !=null) {
			this.superClassName = ClassUtil.convertResourcePathToClassPath(supername);
		}
		this.interfaces = new String[interfaces.length];
		for (int i =0 ;i<interfaces.length;i++) {
			this.interfaces[i] = ClassUtil.convertResourcePathToClassPath(interfaces[i]);
		}
	}
	
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public String getSuperClassName() {
		return superClassName;
	}

	public void setSuperClassName(String superClassName) {
		this.superClassName = superClassName;
	}

	public String[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(String[] interfaces) {
		this.interfaces = interfaces;
	}

	@Override
	public boolean hasSuperClass() {
		return false;
	}
}
