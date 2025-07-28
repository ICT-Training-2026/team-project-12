package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.InputData;
import com.example.demo.entity.MyPage;
import com.example.demo.form.EmployeeNumForm;
import com.example.demo.form.InputDataForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.NameService;
import com.example.demo.service.SearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NormalSearchResultController {
	
	private final SearchService service;
	private final NameService nservice;
	
	@PostMapping("/my_page_nresult")
	public String returnMyPage(@ModelAttribute EmployeeNumForm empform, @ModelAttribute MyForm myform,Model model) {
		
		
		MyPage tmp = new MyPage();
		
		tmp = nservice.MyReference(empform.getEmployeeNum());
		
		model.addAttribute("employeenum",empform.getEmployeeNum());
		model.addAttribute("name",tmp.getName());
		model.addAttribute("hours",tmp.getHours());
		
		model.addAttribute("employeeNum",empform.getEmployeeNum());
		
		return "my-page";
	}
	
	@PostMapping("/return_normal_search")
	public String returnNormalSearch(@ModelAttribute MyForm myform, @ModelAttribute EmployeeNumForm empform, @ModelAttribute InputDataForm inputform, Model model) {
		
		model.addAttribute("employeenum",myform.getEmployeeNum());
		model.addAttribute("employeeNum",myform.getEmployeeNum());
		
		InputData data = new InputData();
		
		data = service.NormalEmployeeInfo(myform.getEmployeeNum());
		
		System.out.println(data);
		
		model.addAttribute("empName",data.getEmpName());
		model.addAttribute("empNum",myform.getEmployeeNum());
		model.addAttribute("composeName",data.getComposeName());
		
		inputform.setEmployeeNum(myform.getEmployeeNum());
		inputform.setEmpName(data.getEmpName());
		inputform.setEmpNum(myform.getEmployeeNum());
		inputform.setComposeId(data.getComposeId());
		
		return "search-normal";
	}
	
}
