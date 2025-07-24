package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Regist;
import com.example.demo.form.AttendForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.RegistConfirmService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistConfirmController {
	
	private final RegistConfirmService rservice;
	
	// 勤怠登録画面へ戻る
	@PostMapping("return_regist_attend")
	public String returnRegistAttend(@ModelAttribute MyForm myform, @ModelAttribute AttendForm attendform, Model model) {
		
		model.addAttribute("employeenum",attendform.getEmployeeNum());
		
		System.out.println("RegistConfirmController returnRegistAttend : " + attendform);
		
		return "regist-attend";
	}
	
	@PostMapping("regist_complete_page")
	public String registCompletePage(@ModelAttribute MyForm myform, @ModelAttribute AttendForm attendform, Model model) {
		
		Regist regist = new Regist();
		
		regist.setEmployeeNum(attendform.getEmployeeNum());
		regist.setYear(attendform.getYear());
		regist.setAttendanceType(attendform.getAttendanceType());
		regist.setStartHour(attendform.getStartHour());
		regist.setStartMinute(attendform.getStartMinute());
		regist.setFinishHour(attendform.getFinishHour());
		regist.setFinishMinute(attendform.getFinishMinute());
		regist.setRestTime(attendform.getRestTime());
		
		
		rservice.registBridge(regist);
		
		
		
		
		model.addAttribute("employeenum",attendform.getEmployeeNum());
		
		return "regist-complete";
	}
	
}
