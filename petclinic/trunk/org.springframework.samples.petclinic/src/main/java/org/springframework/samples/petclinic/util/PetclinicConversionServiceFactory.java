package org.springframework.samples.petclinic.util;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.ui.format.jodatime.JodaTimeFormattingConfigurer;
import org.springframework.ui.format.support.FormattingConversionService;

public class PetclinicConversionServiceFactory implements FactoryBean<ConversionService>, InitializingBean {

	private FormattingConversionService conversionService = new FormattingConversionService();
	
	// implementing InitializingBean
	
	public void afterPropertiesSet() {
		installJodaTimeFormatting();
	}

	// implementing FactoryBean
	
	public ConversionService getObject() throws Exception {
		return conversionService;
	}

	public Class<? extends ConversionService> getObjectType() {
		return ConversionService.class;
	}

	public boolean isSingleton() {
		return true;
	}

	// internal helpers
	
	private void installJodaTimeFormatting() {		
		JodaTimeFormattingConfigurer configurer = new JodaTimeFormattingConfigurer(conversionService);
		configurer.registerJodaTimeFormatting();
	}
}
