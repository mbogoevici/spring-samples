package org.springframework.samples.mvc.fileupload;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

	@RequestMapping(method=RequestMethod.GET)
	public void fileUploadForm(HttpSession session, Model model) {
		String message = (String) session.getAttribute("message");
		if (message != null) {
			model.addAttribute("message", message);
			session.removeAttribute("message");
		}
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processUpload(@RequestParam MultipartFile file, HttpSession session) throws IOException {
		session.setAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
		return "redirect:/fileupload";
	}
	
}
