package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MyPage;
import com.example.demo.form.AttendForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.NameService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistController {
	
	private final NameService nservice;
	
	
	// マイページへ戻る
	@PostMapping("return_mypage")
	public String returnMypage(@ModelAttribute MyForm myform, Model model) {

		System.out.println("RegistController myPage : " + myform);
		
		MyPage tmp = new MyPage();
		
		
		tmp = nservice.MyReference(myform.getEmployeeNum());
		
		model.addAttribute("employeenum",myform.getEmployeeNum());
		model.addAttribute("name",tmp.getName());
		model.addAttribute("hours",tmp.getHours());
		
		

		model.addAttribute("employeenum", myform.getEmployeeNum());


		return "my-page";
	}
	
	
	@PostMapping("regist_confirm")
	public String registConfirm(@ModelAttribute MyForm myform, @ModelAttribute AttendForm attendform) {
		
		
		return "regist-confirm";
	}
	
	
}
