package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.EmployeeNumForm;

@Controller
public class SpecialSearchResultController {
	
	@PostMapping("/my_page_spresult")
	public String returnSpecialSearchPage(@ModelAttribute EmployeeNumForm empnumform) {
		
		return "my-page";
	}
	
}
