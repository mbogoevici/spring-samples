package org.springframework.samples.petcare;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class ContainerInitTests {

	private XmlWebApplicationContext context;
	
	@Before
	public void setup() {
		context = new XmlWebApplicationContext();
		context.setServletContext(new MockServletContext("src/main/webapp", new FileSystemResourceLoader()));
		context.setConfigLocations(new String[] {
				"classpath:/META-INF/spring/root-context.xml",
				"classpath:/META-INF/spring/appServlet/servlet-context.xml"
			});
		context.refresh();
	}
	
	@Test
	public void testOk() {
		
	}
}
