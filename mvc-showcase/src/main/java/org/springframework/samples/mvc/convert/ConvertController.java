package org.springframework.samples.mvc.convert;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/convert/*")
public class ConvertController {

	@RequestMapping("primitive")
	public @ResponseBody String primitive(@RequestParam Integer number) {
		return "Converted " + number;
	}

	// requires Joda time library on classpath
	@RequestMapping("date/{date}")
	public @ResponseBody String date(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date date) {
		return "Converted " + date;
	}
	
	@RequestMapping("collection")
	public @ResponseBody String collection(@RequestParam List<Integer> numbers) {
		return "Converted " + numbers;
	}
	
	@RequestMapping("bean")
	public @ResponseBody String bean(JavaBean bean) {
		return "Converted " + bean;
	}
	
	@RequestMapping("value")
	public @ResponseBody String valueObject(@RequestParam SocialSecurityNumber ssn) {
		return "Converted " + ssn;
	}

}
