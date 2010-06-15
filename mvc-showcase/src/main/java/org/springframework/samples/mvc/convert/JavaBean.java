package org.springframework.samples.mvc.convert;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class JavaBean {
	
	private Integer number;
	
	@DateTimeFormat(iso=ISO.DATE)
	private Date date;
	
	// list will autogrow as its dereferenced e.g. numbers[0]=foo
	private List<Integer> numbers;
	
	// map will autogrow as its deferenced e.g. fruits[1]=apple
	private Map<Integer, String> fruits;
	
}
