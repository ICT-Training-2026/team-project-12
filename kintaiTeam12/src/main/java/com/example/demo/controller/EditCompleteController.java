package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MyPage;
import com.example.demo.form.EmployeeNumForm;
import com.example.demo.form.MyForm;
import com.example.demo.form.SummaryForm;
import com.example.demo.service.NameService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditCompleteController {
	
	private final NameService nservice;
	
	
	// マイページへ戻る
	@PostMapping("edit_return_mypage")
	public String returnMypage(@ModelAttribute MyForm myform, @ModelAttribute SummaryForm summaryform, Model model, @ModelAttribute EmployeeNumForm empform) {

		MyPage tmp = new MyPage();
		
		System.out.println(myform);
		
		myform.setEmployeeNum(summaryform.getEmployeeNum());
		
		model.addAttribute("employeeNum",myform.getEmployeeNum());
		
		tmp = nservice.MyReference(myform.getEmployeeNum());
		
		model.addAttribute("employeenum", myform.getEmployeeNum());
		model.addAttribute("name", tmp.getName());
		model.addAttribute("hours", tmp.getHours());
		

		return "my-page";
	}

}
