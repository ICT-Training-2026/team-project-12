package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExportController {
	
	@PostMapping("/personal_page_ex")
	public String returnPersonalPage() {
		return "personal-page";
	}

}
