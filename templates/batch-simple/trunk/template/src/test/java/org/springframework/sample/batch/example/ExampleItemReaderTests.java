package org.springframework.sample.batch.example;

import org.springframework.sample.batch.example.ExampleItemReader;

import junit.framework.TestCase;

public class ExampleItemReaderTests extends TestCase {

	private ExampleItemReader reader = new ExampleItemReader();
	
	public void testReadOnce() throws Exception {
		assertEquals("Hello world!", reader.read());
	}

	public void testReadTwice() throws Exception {
		reader.read();
		assertEquals(null, reader.read());
	}

}
