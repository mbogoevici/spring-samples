package org.springframework.samples.mvc.basic;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/example")
public class ExampleController {

	@RequestMapping(method=RequestMethod.GET)
	public TestBean showForm() {
		return new TestBean();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@Valid TestBean bean, BindingResult result) {
		return "redirect:/example";
	}
	
	private static class TestBean {
		
		@NotNull
		@Size(max=25)
		private String text;
		
		private boolean checkbox;
		
		@NumberFormat(style=Style.CURRENCY)
		private BigDecimal currency;
		
		@NumberFormat(style=Style.PERCENT)
		private BigDecimal percent;

		@DateTimeFormat(style="S-")
		@Future
		private Date date;
		
	}

}
