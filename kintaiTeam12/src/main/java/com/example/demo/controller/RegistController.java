package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MyPage;
import com.example.demo.form.AttendForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.NameService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistController {
	
	private final NameService nservice;
	
	
	// マイページへ戻る
	@PostMapping("return_mypage")
	public String returnMypage(@ModelAttribute MyForm myform, Model model) {

		System.out.println("RegistController myPage : " + myform);
		
		MyPage tmp = new MyPage();
		
		
		tmp = nservice.MyReference(myform.getEmployeeNum());
		
		model.addAttribute("employeenum",myform.getEmployeeNum());
		model.addAttribute("name",tmp.getName());
		model.addAttribute("hours",tmp.getHours());
		
		
		return "my-page";
	}
	
	
	@PostMapping("regist_confirm")
	public String registConfirm(@ModelAttribute MyForm myform, @ModelAttribute AttendForm attendform, Model model) {
		
		model.addAttribute("employeenum",attendform.getEmployeeNum());
		model.addAttribute("year",attendform.getYear());
		model.addAttribute("attendanceType",attendform.getAttendanceType());
		model.addAttribute("startHour",attendform.getStartHour());
		model.addAttribute("startMinute",attendform.getStartMinute());
		model.addAttribute("finishHour",attendform.getFinishHour());
		model.addAttribute("finishMinute",attendform.getFinishMinute());
		model.addAttribute("restTime",attendform.getRestTime());
		
		attendform.setEmployeeNum(myform.getEmployeeNum());

		
		// 年休のとき、労働時間を固定
		if ("年休".equals(attendform.getAttendanceType())) {

			System.out.println("if年休 時間固定：起動確認");

			attendform.setStartHour(9);
			attendform.setStartMinute(0);
			attendform.setFinishHour(16);
			attendform.setFinishMinute(0);
			
			model.addAttribute("startHour",attendform.getStartHour());
			model.addAttribute("startMinute",attendform.getStartMinute());
			model.addAttribute("finishHour",attendform.getFinishHour());
			model.addAttribute("finishMinute",attendform.getFinishMinute());
			
		}

		System.out.println("RegistController registConfirm : " + attendform);
		
		return "regist-confirm";
	}
	
	
}
