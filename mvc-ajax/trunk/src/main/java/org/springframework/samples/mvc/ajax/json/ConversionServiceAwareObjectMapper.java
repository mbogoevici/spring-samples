package org.springframework.samples.mvc.ajax.json;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.BasicClassIntrospector;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

public class ConversionServiceAwareObjectMapper extends ObjectMapper {

	@Autowired
	public ConversionServiceAwareObjectMapper(ConversionService conversionService) {
		AnnotationIntrospector introspector = AnnotationIntrospector.pair(new FormatAnnotationIntrospector(conversionService), new JacksonAnnotationIntrospector());
		SerializationConfig sconfig = new SerializationConfig(BasicClassIntrospector.instance, introspector);
		DeserializationConfig dconfig = new DeserializationConfig(BasicClassIntrospector.instance, introspector);
		setSerializationConfig(sconfig);
		setDeserializationConfig(dconfig);
	}
}
