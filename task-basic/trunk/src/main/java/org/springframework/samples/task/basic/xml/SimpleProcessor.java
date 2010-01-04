package org.springframework.samples.task.basic.xml;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.samples.task.basic.Processor;
import org.springframework.stereotype.Service;

@Service
public class SimpleProcessor implements Processor {

	private final AtomicInteger counter = new AtomicInteger();

	public void process() {
		System.out.println("processing next 10 at " + new Date());
		for (int i = 0; i < 10; i++) {
			System.out.println("   processing " + counter.incrementAndGet());
		}
	}

}
