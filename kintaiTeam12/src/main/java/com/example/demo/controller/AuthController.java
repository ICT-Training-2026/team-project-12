package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MyPage;
import com.example.demo.form.EmployeeNumForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.NameService;
import com.example.demo.service.PersonalService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
	
	private final NameService nservice;
	private final PersonalService pservice;
	
	@PostMapping("my_page")
	public String myPage(@ModelAttribute EmployeeNumForm empform, @ModelAttribute MyForm myform,Model model) {
		
		MyPage tmp = new MyPage();
		
		System.out.println("AuthController myPage : " + empform);
		
		tmp = nservice.MyReference(empform.getEmployeeNum());
		
		model.addAttribute("employeenum",empform.getEmployeeNum());
		model.addAttribute("name",tmp.getName());
		model.addAttribute("hours",tmp.getHours());
		
		return "my-page";
	}
	
	@PostMapping("personal_page")
	public String personalPage(@ModelAttribute EmployeeNumForm empform, @ModelAttribute MyForm myform,Model model) {
		
		String compose = pservice.personalReference(empform.getEmployeeNum());
		
		
		if(compose.equals("1")||compose.equals("2")){
			
			return "personal-page";
			
		}else {
			
			model.addAttribute("message","あなたには権限がありません");
			return "login-success";
			
		}
		
		
		
	}
}
