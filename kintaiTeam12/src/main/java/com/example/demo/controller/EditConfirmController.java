package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Summary;
import com.example.demo.form.EmployeeNumForm;
import com.example.demo.form.MyForm;
import com.example.demo.form.SummaryForm;
import com.example.demo.service.EditConfirmService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditConfirmController {
	
	private final EditConfirmService eservice;
	
	
	// 編集画面へ戻る
	@PostMapping("edit_return_page")
	public String returnEditPage(@ModelAttribute MyForm myform, @ModelAttribute SummaryForm summaryform,Model model, @ModelAttribute EmployeeNumForm empform) {
		
		model.addAttribute("employeenum",summaryform.getEmployeeNum());
		model.addAttribute("employeeNum",myform.getEmployeeNum());
		
		System.out.println("EditConfirmController 編集画面へ戻る : " + summaryform);
		
		return "edit";
	}
	
//	編集完了画面にいく
	@PostMapping("edit_complete_page")
	public String editCompletePage(@ModelAttribute MyForm myform, @ModelAttribute SummaryForm summaryform, Model model, @ModelAttribute EmployeeNumForm empform) {
		
		model.addAttribute("employeeNum",myform.getEmployeeNum());
		
		Summary summary = new Summary();
		
		summary.setEmployeeNum(summaryform.getEmployeeNum());
		summary.setDate(summaryform.getDate());
		summary.setAttendanceType(summaryform.getAttendanceType());
		summary.setStartHour(summaryform.getStartHour());
		summary.setStartMinute(summaryform.getStartMinute());
		summary.setFinishHour(summaryform.getFinishHour());
		summary.setFinishMinute(summaryform.getFinishMinute());
		summary.setRestTime(summaryform.getRestTime());
		
		summary.setAttendanceId(summaryform.getAttendanceId());
		
		
		eservice.editBridge(summary);
		
		model.addAttribute("employeenum",summaryform.getEmployeeNum());
		
		return "edit-complete";
	}
	
}
