package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.InputData;
import com.example.demo.entity.Result;
import com.example.demo.form.EmployeeNumForm;
import com.example.demo.form.InputDataForm;
import com.example.demo.service.NormalResultService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NormalSearchController {
	
	private final NormalResultService sservice;
	
	@PostMapping("/search_normal_result")
	public String normalResult(@ModelAttribute InputDataForm inputdataform,Model model,@ModelAttribute EmployeeNumForm empnumform) {
		
		//System.out.println(inputdataform);
		//InputDataForm(employeeNum=000003, date=2025-07-01, empName=佐藤 三郎, empNum=000003, composeId=3)
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
		
		model.addAttribute("employeeNum",inputdataform.getEmployeeNum());
		
		empnumform.setEmployeeNum(inputdataform.getEmployeeNum());
		
		return "search-normal-result";
	}
	
//	@PostMapping()
//	public String returnMyPage() {
//		
//	}
}
