package org.springframework.samples.petclinic.util;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.ui.format.support.GenericFormatterRegistry;

public class PetclinicFormatterRegistry extends GenericFormatterRegistry implements InitializingBean {

	public void afterPropertiesSet() throws Exception {
		ConverterRegistry converterRegistry = getConverterRegistry();
		converterRegistry.addConverter(new DateTimeToLocalDateConverter());
		converterRegistry.addConverter(new LocalDateToDateTimeConverter());
		converterRegistry.addConverter(new DateTimeToLocalTimeConverter());
		converterRegistry.addConverter(new LocalTimeToDateTimeConverter());

		addFormatterByType(DateTime.class, new DateTimeFormatter("yyyy-MM-dd hh:mm:ss"));
		addFormatterByType(LocalDate.class, new DateTimeFormatter("yyyy-MM-dd"));
		addFormatterByType(LocalTime.class, new DateTimeFormatter("hh:mm:ss"));
	}

	private static class DateTimeToLocalDateConverter implements Converter<DateTime, LocalDate> {
		public LocalDate convert(DateTime dateTime) {
			return dateTime.toLocalDate();
		}		
	}

	private static class LocalDateToDateTimeConverter implements Converter<LocalDate, DateTime> {
		public DateTime convert(LocalDate localDate) {
			return localDate.toDateTimeAtStartOfDay();
		}		
	}

	private static class DateTimeToLocalTimeConverter implements Converter<DateTime, LocalTime> {
		public LocalTime convert(DateTime dateTime) {
			return dateTime.toLocalTime();
		}		
	}

	private static class LocalTimeToDateTimeConverter implements Converter<LocalTime, DateTime> {
		public DateTime convert(LocalTime localTime) {
			return localTime.toDateTimeToday();
		}		
	}

	protected ConverterRegistry getConverterRegistry() {
		return (ConverterRegistry) super.getConversionService();
	}

}
