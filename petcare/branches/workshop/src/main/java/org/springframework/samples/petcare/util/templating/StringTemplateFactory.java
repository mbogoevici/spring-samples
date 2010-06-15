package org.springframework.samples.petcare.util.templating;

import org.springframework.core.io.Resource;

public interface StringTemplateFactory {
	
	StringTemplate getStringTemplate(Resource resource);
		
}
