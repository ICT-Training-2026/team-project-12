package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MyPage;
import com.example.demo.form.EmployeeNumForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.NameService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NormalSearchResultController {
	
	private final NameService nservice;
	
	@PostMapping("/my_page_nresult")
	public String returnNormalSearchPage(@ModelAttribute EmployeeNumForm empform, @ModelAttribute MyForm myform,Model model) {
		
		
		MyPage tmp = new MyPage();
		
		tmp = nservice.MyReference(empform.getEmployeeNum());
		
		model.addAttribute("employeenum",empform.getEmployeeNum());
		model.addAttribute("name",tmp.getName());
		model.addAttribute("hours",tmp.getHours());
		
		model.addAttribute("employeeNum",empform.getEmployeeNum());
		
		return "my-page";
	}
	
}
