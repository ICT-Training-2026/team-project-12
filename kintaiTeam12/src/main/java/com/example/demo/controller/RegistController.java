package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AttendForm;
import com.example.demo.form.MyForm;

@Controller
public class RegistController {
	
	// マイページへ戻る
	@PostMapping("return_mypage")
	public String returnMypage(@ModelAttribute MyForm myform) {
		
		return "my-page";
	}
	
	
	@PostMapping("regist_confirm")
	public String registConfirm(@ModelAttribute MyForm myform, @ModelAttribute AttendForm attendform) {
		
		
		return "regist-confirm";
	}
	
	
}
