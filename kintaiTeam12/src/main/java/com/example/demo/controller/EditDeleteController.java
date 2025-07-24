package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.InputData;
import com.example.demo.entity.Result;
import com.example.demo.form.AttendForm;
import com.example.demo.form.InputDataForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.EditDeleteService;
import com.example.demo.service.NameService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditDeleteController {
	
	private final NameService ednservice;
	private final EditDeleteService edservice;
	
	
	// マイページへ戻る
	@PostMapping("edit_delete_return_mypage")
	public String returnMyPage() {		
		
		return "my-page";
	}
	
	@PostMapping("edit_page")
	public String editPage(@ModelAttribute InputDataForm inputdataform, @ModelAttribute AttendForm attendform, Model model) {
		
		InputData tmp = new InputData();
		tmp.setEmployeeNum(inputdataform.getEmployeeNum());
		
		tmp.setDate(inputdataform.getDate());
		tmp.setEmpName(inputdataform.getEmpName());
		tmp.setEmpNum(inputdataform.getEmpNum());
		tmp.setComposeId(inputdataform.getComposeId());
		
		List<Result> tmp1 = edservice.specialSearchBridge(tmp);
		
		if(tmp1.size()>0) {
			model.addAttribute("resultList",tmp1);
		}
		
		empnumform.setEmployeeNum(inputdataform.getEmployeeNum());
		
		return "edit";
	}
	
	
	@PostMapping("delete_page")
	public String deletePage(@ModelAttribute MyForm myform,@ModelAttribute AttendForm attendform, Model model){
		
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
	

