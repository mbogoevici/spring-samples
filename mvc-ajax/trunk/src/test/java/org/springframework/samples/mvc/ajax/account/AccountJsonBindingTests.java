package org.springframework.samples.mvc.ajax.account;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import junit.framework.TestCase;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.samples.mvc.ajax.json.ConversionServiceAwareObjectMapper;
import org.springframework.util.Assert;

public class AccountJsonBindingTests extends TestCase{

	Logger log = LoggerFactory.getLogger(AccountJsonBindingTests.class);
	
	private MappingJacksonHttpMessageConverter converter;
	
	private ObjectMapper objectMapper;
	
	private String testPayload = "{\"name\":\"jim\",\"id\":null,\"balance\":\"$1,000.00\",\"equityAllocation\":\"60%\",\"renewalDate\":\"1/22/11\"}";

	@Override
	public void setUp() {
		FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
		factoryBean.afterPropertiesSet();
		ConversionService conversionService = factoryBean.getObject();
		
		objectMapper = new ConversionServiceAwareObjectMapper(conversionService);
		converter = new MappingJacksonHttpMessageConverter();
		converter.setObjectMapper(objectMapper);
	}
	
	@SuppressWarnings("unchecked")
	public void testAnnotationDrivenJsonConversion() throws Exception {
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(testPayload.getBytes("UTF-8"));
		MediaType jsonType = new MediaType("application", "json");
		inputMessage.getHeaders().setContentType(jsonType);
		Account account = (Account) converter.read((Class) Account.class, inputMessage);
		Assert.notNull(account);
		log.debug(account.toString());
		
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		converter.write(account, jsonType, outputMessage);
		log.debug(outputMessage.getBody().toString());
		assertEquals("Incoming and outgoing JSON representations expected to match", testPayload, outputMessage.getBody().toString());
	}
	
	private static class MockHttpInputMessage implements HttpInputMessage {

		private final HttpHeaders headers = new HttpHeaders();

		private final InputStream body;

		public MockHttpInputMessage(byte[] contents) {
			Assert.notNull(contents, "'contents' must not be null");
			this.body = new ByteArrayInputStream(contents);
		}

		public HttpHeaders getHeaders() {
			return headers;
		}

		public InputStream getBody() throws IOException {
			return body;
		}
	}
	
	public class MockHttpOutputMessage implements HttpOutputMessage {

		private final HttpHeaders headers = new HttpHeaders();

		private final ByteArrayOutputStream body = new ByteArrayOutputStream();

		public HttpHeaders getHeaders() {
			return headers;
		}

		public OutputStream getBody() throws IOException {
			return body;
		}

		public byte[] getBodyAsBytes() {
			return body.toByteArray();
		}

		public String getBodyAsString(Charset charset) {
			byte[] bytes = getBodyAsBytes();
			return new String(bytes, charset);
		}
	}
}
