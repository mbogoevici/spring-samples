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
	public TestModel showForm() {
		return new TestModel();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processFormSubmit(@Valid TestModel bean, BindingResult result) {
		if (result.hasErrors()) {
			// re-render form with errors
			return "example";
		}
		return "redirect:/example";
	}
	
	
	public static class TestModel {
		
		@NotNull
		@Size(max=25)
		private String text = "Default Value";
		
		private boolean checkbox;
		
		@NumberFormat(style=Style.CURRENCY)
		private BigDecimal currency = new BigDecimal("3000");
		
		@NumberFormat(style=Style.PERCENT)
		private BigDecimal percent = new BigDecimal(".25");

		@DateTimeFormat(style="S-")
		@Future
		private Date date = new Date();

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public boolean isCheckbox() {
			return checkbox;
		}

		public void setCheckbox(boolean checkbox) {
			this.checkbox = checkbox;
		}

		public BigDecimal getCurrency() {
			return currency;
		}

		public void setCurrency(BigDecimal currency) {
			this.currency = currency;
		}

		public BigDecimal getPercent() {
			return percent;
		}

		public void setPercent(BigDecimal percent) {
			this.percent = percent;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
		
	}

}
