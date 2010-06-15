package org.springframework.samples.petcare.util.templating;

import org.springframework.core.io.Resource;
import org.springframework.integration.transformer.AbstractPayloadTransformer;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

public class ObjectToStringTransformer extends AbstractPayloadTransformer<Object, String> {

	private Resource templateResource;
	
	private StringTemplateFactory templateFactory = new DefaultStringTemplateFactory();
	
	public void setTemplate(Resource template) {
		templateResource = template;
	}

	protected String transformPayload(Object payload) throws Exception {
		if (templateResource != null) {
			StringTemplate template = templateFactory.getStringTemplate(templateResource);
			String payloadAttributeName = getPayloadAttributeName(payload);
			template.setAttribute(payloadAttributeName, payload);
			return template.toString();
		} else {
			return payload.toString();
		}
	}
	
	private String getPayloadAttributeName(Object payload) {
		return StringUtils.uncapitalize(ClassUtils.getShortName(payload.getClass()));
	}
	

}
