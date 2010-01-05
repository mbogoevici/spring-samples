package org.springframework.samples.task.basic.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlDemo {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("config.xml", XmlDemo.class);
	}

}
