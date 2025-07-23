package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MyPage;
import com.example.demo.form.EmployeeNumForm;
import com.example.demo.service.ExportService;
import com.example.demo.service.NameService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class PersonalContoroller {
	
	private final NameService nservice;
	private final ExportService eservice;
	
	@PostMapping("/my_page_personal")
	public String myPage(@ModelAttribute EmployeeNumForm empform, Model model) {
		
		//System.out.println(empform);
		
		MyPage tmp = new MyPage();
		
		tmp = nservice.MyReference(empform.getEmployeeNum());
		
		model.addAttribute("employeenum",empform.getEmployeeNum());
		model.addAttribute("name",tmp.getName());
		model.addAttribute("hours",tmp.getHours());
		
		return "my-page";
	}
	
	@PostMapping("/personal_export")
	public String exportPage(@ModelAttribute EmployeeNumForm empform) {
		
		eservice.exportBridge();
		
		return "personal-export";
	}
}
