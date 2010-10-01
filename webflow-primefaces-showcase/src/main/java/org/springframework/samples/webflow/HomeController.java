package org.springframework.samples.webflow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	private static final Map<String, Integer> tabNameToIndex = new HashMap<String, Integer>();

	static {
		tabNameToIndex.put("ajax", 0);
		tabNameToIndex.put("modal", 1);
		tabNameToIndex.put("springSecurity", 2);
	}
	
	@RequestMapping("/home")
	public void setTab(@RequestParam(value="activeTab", required=false) String key, Model model) {
		if ((key == null) || (! tabNameToIndex.containsKey(key))) {
			model.addAttribute("activeTabIndex", 0);
		} else {
			model.addAttribute("activeTabIndex", tabNameToIndex.get(key));
		}
	}
	
}
