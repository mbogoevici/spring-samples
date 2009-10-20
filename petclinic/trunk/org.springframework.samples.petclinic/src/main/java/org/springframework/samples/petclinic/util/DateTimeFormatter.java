package org.springframework.samples.petclinic.util;

import java.text.ParseException;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.ui.format.Formatter;

/**
 * {@link Formatter} implementation to handle JodaTime {@link DateTime} instances.
 */
public class DateTimeFormatter implements Formatter<DateTime> {

	private org.joda.time.format.DateTimeFormatter formatter;

	public DateTimeFormatter(String pattern) {
		this.formatter = DateTimeFormat.forPattern(pattern);
	}

	public String format(DateTime object, Locale locale) {
		return null == object ? "" : object.toString(formatter);
	}

	public DateTime parse(String formatted, Locale locale)
			throws ParseException {
		if ("".equals(formatted)) {
			return null;
		} else {
			return this.formatter.withLocale(locale).parseDateTime(formatted);
		}
	}
}
