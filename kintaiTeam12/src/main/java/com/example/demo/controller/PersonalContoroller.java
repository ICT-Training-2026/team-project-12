package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.EmployeeNumForm;

@Controller
public class PersonalContoroller {
	
	@PostMapping("/my_page_personal")
	public String myPage(@ModelAttribute EmployeeNumForm empform ) {
		
		//System.out.println(empform);
		
		return "my-page";
	}
	
	@PostMapping("/personal_export")
	public String exportPage() {
		
		
		return "personal-export";
	}
}
