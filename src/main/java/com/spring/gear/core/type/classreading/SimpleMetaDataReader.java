package com.spring.gear.core.type.classreading;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.asm.ClassReader;

import com.spring.gear.core.io.Resource;
import com.spring.gear.core.type.AnnotationMetadata;
import com.spring.gear.core.type.ClassMetadata;

public class SimpleMetaDataReader implements MetadataReader {
	
	private final Resource resource;
	
	private final ClassMetadata classMetadata;
	
	private final AnnotationMetadata annotationMetadata;
	
	
	public SimpleMetaDataReader(Resource resource) throws Exception {
		InputStream in = new BufferedInputStream(resource.getInputStream());
		ClassReader classReader;
		try {
			classReader = new ClassReader(in);
		}finally {
			in.close();
		}
		
		AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
		classReader.accept(visitor, ClassReader.SKIP_DEBUG);
		this.resource = resource;
		this.annotationMetadata = visitor;
		this.classMetadata = visitor;
	}

	@Override
	public Resource getResource() {
		
		return this.resource;
	}

	@Override
	public ClassMetadata getClassMetadata() {
		return this.classMetadata;
	}

	@Override
	public AnnotationMetadata getAnotationMetadata() {
		return this.annotationMetadata;
	}

}
