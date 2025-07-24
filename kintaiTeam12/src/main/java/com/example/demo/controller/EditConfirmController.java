package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AttendForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.NameService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditConfirmController {
	
	private final NameService nservice;
	
	
	// 編集画面へ戻る
	@PostMapping("edit_return_page")
	public String returnEditPage(@ModelAttribute MyForm myform, @ModelAttribute AttendForm attendform,Model model) {
		
//		model.addAttribute("employeenum",attendform.getEmployeeNum());
//		
//		System.out.println("RegistConfirmController returnRegistAttend : " + attendform);

		return "edit";
	}
	
//	編集完了画面にいく
	@PostMapping("edit_complete_page")
	public String editCompletePage(@ModelAttribute MyForm myform, @ModelAttribute AttendForm attendform, Model model) {
		
//		Regist regist = new Regist();
		
//		regist.setEmployeeNum(attendform.getEmployeeNum());
//		regist.setYear(attendform.getYear());
//		regist.setStartHour(attendform.getStartHour());
//		regist.setStartMinute(attendform.getStartMinute());
//		regist.setFinishHour(attendform.getFinishHour());
//		regist.setFinishMinute(attendform.getFinishMinute());
//		regist.setRestTime(attendform.getRestTime());
//		
//		
//		rservice.registBridge(regist);
//		
//		model.addAttribute("employeenum",attendform.getEmployeeNum());
		
		return "edit-complete";
	}
	
}
