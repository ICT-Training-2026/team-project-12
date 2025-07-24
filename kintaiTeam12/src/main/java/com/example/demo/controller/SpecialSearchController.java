package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.InputData;
import com.example.demo.entity.MyPage;
import com.example.demo.entity.Result;
import com.example.demo.form.EmployeeNumForm;
import com.example.demo.form.InputDataForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.NameService;
import com.example.demo.service.SpecialResultService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SpecialSearchController {
	
	private final NameService nservice;
	private final SpecialResultService sservice;
	
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
	
	@PostMapping("/search_special_result")
	public String specialResult(@ModelAttribute InputDataForm inputdataform,Model model,@ModelAttribute EmployeeNumForm empnumform) {
		
		InputData tmp = new InputData();
		tmp.setEmployeeNum(inputdataform.getEmployeeNum());
		
		tmp.setDate(inputdataform.getDate());
		tmp.setEmpName(inputdataform.getEmpName());
		tmp.setEmpNum(inputdataform.getEmpNum());
		tmp.setComposeId(inputdataform.getComposeId());
		
		List<Result> tmp1 = sservice.specialSearchBridge(tmp);
		
		if(tmp1.size()>0) {
			model.addAttribute("resultList",tmp1);
		}
		
		empnumform.setEmployeeNum(inputdataform.getEmployeeNum());
		
		return "search-special-result";
	}
}
