package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AttendForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.RegistConfirmService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditController {
	
	private final RegistConfirmService rservice;
	
	// 編集削除のトップ画面へ戻る
	@PostMapping("return_edit_delete")
	public String returnEditDeletePage(@ModelAttribute AttendForm attendform, Model model) {
		
		model.addAttribute("employeenum",attendform.getEmployeeNum());
		
		System.out.println("RegistConfirmController returnRegistAttend : " + attendform);
		
		return "edit-delete";
	}
	
	@PostMapping("edit_confirm_page")
	public String editConfirmPage(@ModelAttribute MyForm myform, @ModelAttribute AttendForm attendform, Model model) {
		
		model.addAttribute("employeenum",attendform.getEmployeeNum());
		model.addAttribute("year",attendform.getYear());
		model.addAttribute("attendanceType",attendform.getAttendanceType());
		model.addAttribute("startHour",attendform.getStartHour());
		model.addAttribute("startMinute",attendform.getStartMinute());
		model.addAttribute("finishHour",attendform.getFinishHour());
		model.addAttribute("finishMinute",attendform.getFinishMinute());
		model.addAttribute("restTime",attendform.getRestTime());
		
		attendform.setEmployeeNum(myform.getEmployeeNum());
		
		System.out.println("RegistController registConfirm : " + attendform);
		
		return "edit-confirm";
	}
	
}
