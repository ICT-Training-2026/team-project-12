package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LoginForm;

@Controller
public class LogoutController {

	@PostMapping("/logout_page")
	public String logoutPage(@ModelAttribute LoginForm loginform,Model model) {
		
		System.out.println(loginform);
		System.out.println(model);
		
		return "logout-page";
	}
	
}
