package org.springframework.samples.mvc.messageconverters;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.syndication.feed.atom.Feed;

@Controller
@RequestMapping("messageconverters/*")
public class MessageConvertersController {

	// StringHttpMessageConverter 

	@RequestMapping(value="/string", method=RequestMethod.POST, headers={"Content-Type=text/*", "Accept=text/plain" })
	public @ResponseBody String readString(@RequestBody String string) {
		return "Read string '" + string + "'";
	}
	
	@RequestMapping(value="/string", method=RequestMethod.GET, headers="Accept=text/plain")
	public @ResponseBody String writeString() {
		return "Wrote a string";
	}

	// FormHttpMessageConverter 
	
	@RequestMapping(value="/form", method=RequestMethod.POST, headers={"Content-Type=application/x-www-form-urlencoded", "Accept=text/plain" })
	public @ResponseBody String readForm(@RequestBody MultiValueMap<String, String> form) {
		return "Read form map " + form;
	}
	
	@RequestMapping(value="/form", method=RequestMethod.GET, headers="Accept=application/x-www-form-urlencoded")
	public @ResponseBody MultiValueMap<String, String> writeForm() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("foo", "bar");
		map.add("fruit", "apple");
		return map;
	}

	// Jaxb2RootElementHttpMessageConverter
	
	@RequestMapping(value="/xml", method=RequestMethod.POST, headers={"Content-Type=application/xml", "Accept=text/plain" })
	public @ResponseBody String readXml(@RequestBody JavaBean bean) {
		return "Read from xml " + bean;
	}
	
	@RequestMapping(value="/xml", method=RequestMethod.GET, headers="Accept=application/xml")
	public @ResponseBody JavaBean writeXml() {
		return new JavaBean();
	}

	// MappingJacksonHttpMessageConverter
	
	@RequestMapping(value="/json", method=RequestMethod.POST, headers={"Content-Type=application/json", "Accept=text/plain" })
	public @ResponseBody String readJson(@RequestBody JavaBean bean) {
		return "Read from json " + bean;
	}
	
	@RequestMapping(value="/json", method=RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody JavaBean writeJson() {
		return new JavaBean();
	}

	// AtomFeedHttpMessageConverter
	
	@RequestMapping(value="/atom", method=RequestMethod.GET, headers="Accept=application/atom+xml")
	public @ResponseBody Feed writeFeed() {
		Feed feed = new Feed();
		feed.setFeedType("atom_1.0");
		feed.setTitle("My feed");
		return feed;
	}

}
