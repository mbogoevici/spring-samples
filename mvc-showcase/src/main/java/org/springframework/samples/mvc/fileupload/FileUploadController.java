package org.springframework.samples.mvc.fileupload;

import java.io.IOException;

import org.springframework.samples.mvc.flash.FlashMap.FlashMessage;
import org.springframework.samples.mvc.flash.FlashMap.FlashMessageType;
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
	public void fileUploadForm() {
	}

	@RequestMapping(method=RequestMethod.POST)
	public void processUpload(@RequestParam MultipartFile file, Model model) throws IOException {
		String message = "File '" + file.getOriginalFilename() + "' uploaded successfully";
		// prepare model for rendering success message in this request
		model.addAttribute("message", new FlashMessage(FlashMessageType.success, message));
	}
	
}
