package org.springframework.samples.mvc.fileupload;

import java.io.IOException;

import org.springframework.samples.mvc.flash.FlashMap;
import org.springframework.stereotype.Controller;
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
	public String processUpload(@RequestParam MultipartFile file) throws IOException {
		FlashMap.setSuccessMessage("File '" + file.getOriginalFilename() + "' uploaded successfully");
		return "redirect:/fileupload";
	}
	
}
