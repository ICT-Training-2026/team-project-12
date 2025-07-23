package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.EmployeeNumForm;

@Controller
public class ExportController {
	
	@PostMapping("/personal_page_ex")
	public String returnPersonalPage(@ModelAttribute EmployeeNumForm empform) {
		//System.out.println("test");
		return "personal-page";
	}

}
