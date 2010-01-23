package org.springframework.samples.mvc.ajax.json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public class FormatAnnotationIntrospector extends NopAnnotationIntrospector{

	private final ConversionService conversionService;
	
	public FormatAnnotationIntrospector(ConversionService conversionService) {
		this.conversionService = conversionService;
	}
	
	@Override
	public boolean isHandled(Annotation ann) {
		return ann.toString().startsWith("@org.springframework.format.annotation");
	}

	@Override
	public Object findDeserializer(Annotated a) {
		if (a instanceof AnnotatedMethod) {
			AnnotatedMethod method = (AnnotatedMethod) a;
			TypeDescriptor typeDescriptor = getTypeDescriptorForDeserializer(method);
			for (Annotation ann : typeDescriptor.getAnnotations()){
				if (isHandled(ann)) {
					return new FormattingDeserializer(conversionService, typeDescriptor);
				}
			}
		}
		return null;
	}
	
	@Override
	public Object findSerializer(Annotated a) {
		if (a instanceof AnnotatedMethod) {
			AnnotatedMethod method = (AnnotatedMethod) a;
			TypeDescriptor typeDescriptor = getTypeDescriptorForSerializer(method);
			for (Annotation ann : typeDescriptor.getAnnotations()){
				if (isHandled(ann)) {
					return new FormattingSerializer(conversionService, typeDescriptor);
				}
			}
		}
		return null;
	}

	private TypeDescriptor getTypeDescriptorForDeserializer(AnnotatedMethod method) {
		// TODO - Could handle annotated method params here - in fact, those should probably take precedence over field annotations
		Assert.isTrue(method.getName().startsWith("set"), "Expected a setter method, but was "+method.getName());
		String fieldName = StringUtils.uncapitalize(method.getName().substring(3));
		Field field = ReflectionUtils.findField(method.getDeclaringClass(), fieldName);
		if (field != null) {
			return new TypeDescriptor(field);
		}
		return null;
	}
	
	private TypeDescriptor getTypeDescriptorForSerializer(AnnotatedMethod method) {
		// TODO - Could handle annotated methods here (if that's even valid) - in fact, those should probably take precedence over field annotations
		Assert.isTrue(method.getName().startsWith("get"), "Expected a getter method, but was "+method.getName());
		String fieldName = StringUtils.uncapitalize(method.getName().substring(3));
		Field field = ReflectionUtils.findField(method.getDeclaringClass(), fieldName);
		if (field != null) {
			return new TypeDescriptor(field);
		}
		return null;
	}
	

	

}
