package org.springframework.samples.mvc.data.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

@Controller
public class CustomArgumentController {

	// request related
	
	@Autowired
	public CustomArgumentController(AnnotationMethodHandlerAdapter controllerAdapter) {
		// install custom argument resolver triggered by @RequestAttribute annotation
		controllerAdapter.setCustomArgumentResolver(new WebArgumentResolver() {
			public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
				RequestAttribute attr = methodParameter.getParameterAnnotation(RequestAttribute.class);
				return attr != null ? webRequest.getAttribute(attr.value(), WebRequest.SCOPE_REQUEST) : WebArgumentResolver.UNRESOLVED;
			}
		});
	}
	
	@ModelAttribute
	void beforeInvokingHandlerMethod(HttpServletRequest request) {
		request.setAttribute("attr", "to prove it works!");
	}
	
	@RequestMapping(value="/data/custom", method=RequestMethod.GET)
	public @ResponseBody String custom(@RequestAttribute("attr") String custom) {
		return "Got 'attr' request atribute with value " + custom;
	}

}
