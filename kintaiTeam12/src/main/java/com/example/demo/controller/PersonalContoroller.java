package com.example.demo.controller;

import org.springframework.stereotype.Controller;

@Controller
public class PersonalContoroller {
	
	public String myPage() {
		
		
		return "my-page";
	}
	
	public String exportPage() {
		
		
		return "personal-export";
	}
}
