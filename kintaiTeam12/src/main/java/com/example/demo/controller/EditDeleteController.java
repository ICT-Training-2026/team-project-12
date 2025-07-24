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
public class EditDeleteController {
	
	private final NameService nservice;
	
	
	// マイページへ戻る
	@PostMapping("edit_delete_return_mypage")
	public String returnMypage(@ModelAttribute MyForm myform, Model model) {

//		
//		MyPage tmp = new MyPage();
//		
//		
//		tmp = nservice.MyReference(myform.getEmployeeNum());
//		
//		model.addAttribute("employeenum",myform.getEmployeeNum());
//		model.addAttribute("name",tmp.getName());
//		model.addAttribute("hours",tmp.getHours());
//		
		
		return "my-page";
	}
	
	@PostMapping("edit_page")
	public String editPage(@ModelAttribute MyForm myform, @ModelAttribute AttendForm attendform, Model model) {
		
		model.addAttribute("employeenum",attendform.getEmployeeNum());
		model.addAttribute("year",attendform.getYear());
		model.addAttribute("attendanceType",attendform.getAttendanceType());
		model.addAttribute("startHour",attendform.getStartHour());
		model.addAttribute("startMinute",attendform.getStartMinute());
		model.addAttribute("finishHour",attendform.getFinishHour());
		model.addAttribute("finishMinute",attendform.getFinishMinute());
		model.addAttribute("restTime",attendform.getRestTime());
		
		attendform.setEmployeeNum(myform.getEmployeeNum());
		
		System.out.println("editDeleteController edit : " + attendform);
		
		return "edit";
	}
	
	@PostMapping("delete_page")
	public String deletePage(@ModelAttribute MyForm myform, @ModelAttribute AttendForm attendform, Model model) {
		
		model.addAttribute("employeenum",attendform.getEmployeeNum());
		model.addAttribute("year",attendform.getYear());
		model.addAttribute("attendanceType",attendform.getAttendanceType());
		model.addAttribute("startHour",attendform.getStartHour());
		model.addAttribute("startMinute",attendform.getStartMinute());
		model.addAttribute("finishHour",attendform.getFinishHour());
		model.addAttribute("finishMinute",attendform.getFinishMinute());
		model.addAttribute("restTime",attendform.getRestTime());
		
		attendform.setEmployeeNum(myform.getEmployeeNum());
		
		System.out.println("EditDeleteController delete : " + attendform);
		
		return "delete";
	}
	
	
}
