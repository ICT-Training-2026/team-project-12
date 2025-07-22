package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.form.TestForm;

@Controller
public class TestController {
	@GetMapping("/test")
	public String test(@ModelAttribute TestForm form,Model model) {
		return "test";
	}

}
