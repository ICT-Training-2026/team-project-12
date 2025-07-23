package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.form.EmployeeNumForm;

@Controller
public class PersonalContoroller {
	
	public String myPage(@ModelAttribute EmployeeNumForm empform ) {
		
		System.out.println(empform);
		
		return "my-page";
	}
	
	public String exportPage() {
		
		
		return "personal-export";
	}
}
