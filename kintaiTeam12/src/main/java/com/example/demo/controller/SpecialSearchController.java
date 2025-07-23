package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MyPage;
import com.example.demo.form.InputDataForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.NameService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SpecialSearchController {
	
	private final NameService nservice;
	
	@PostMapping("/my_page_sp")
	public String returnMyPage(@ModelAttribute MyForm myform, Model model) {

		System.out.println("RegistController myPage : " + myform);
		
		MyPage tmp = new MyPage();
		
		
		tmp = nservice.MyReference(myform.getEmployeeNum());
		
		model.addAttribute("employeenum",myform.getEmployeeNum());
		model.addAttribute("name",tmp.getName());
		model.addAttribute("hours",tmp.getHours());
		
		
		return "my-page";
	}
	
	public String specialResult(@ModelAttribute InputDataForm inputdataform) {
		
		System.out.println("test");
		System.out.println(inputdataform);
		
		return "my-page";
	}
}
