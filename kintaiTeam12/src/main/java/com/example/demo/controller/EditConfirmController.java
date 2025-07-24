package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Edit;
import com.example.demo.form.AttendForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.EditConfirmService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditConfirmController {
	
	private final EditConfirmService eservice;
	
	
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
		
		Edit edit = new Edit();
		
		edit.setEmployeeNum(attendform.getEmployeeNum());
		edit.setYear(attendform.getYear());
		edit.setStartHour(attendform.getStartHour());
		edit.setStartMinute(attendform.getStartMinute());
		edit.setFinishHour(attendform.getFinishHour());
		edit.setFinishMinute(attendform.getFinishMinute());
		edit.setRestTime(attendform.getRestTime());
		
		
		eservice.editBridge(edit);
		
		model.addAttribute("employeenum",attendform.getEmployeeNum());
		
		return "edit-complete";
	}
	
}
