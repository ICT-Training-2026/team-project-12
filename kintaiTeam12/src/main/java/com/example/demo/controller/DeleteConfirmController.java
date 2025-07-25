package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Delete;
import com.example.demo.entity.Summary;
import com.example.demo.form.AttendForm;
import com.example.demo.form.MyForm;
import com.example.demo.form.SummaryForm;
import com.example.demo.service.DeleteConfirmService;
import com.example.demo.service.EditDeleteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteConfirmController {
	
	private final DeleteConfirmService dservice;
	private final EditDeleteService eservice;
	
	
	// 編集削除を選ぶ画面へ戻る
	@PostMapping("return_edit_delete2")
	public String returnEditDeletePage2(@ModelAttribute MyForm myform, @ModelAttribute AttendForm registform, Model model) {
		
		model.addAttribute("employeenum",myform.getEmployeeNum());
		
		List<Summary> summaries = eservice.monthAttends(myform.getEmployeeNum());
		
		if(summaries.size()>0) {
			model.addAttribute("resultList",summaries);
		}
		
		return "edit-delete";
	}
	
    //	削除完了画面にいく
	@PostMapping("delete_complete_page")
	public String deleteCompletePage(@ModelAttribute SummaryForm summaryform,Model model,@ModelAttribute MyForm myform) {
		
		//System.out.println(summaryform);
		
		Delete delete = new Delete();
		
		delete.setEmployeeNum(summaryform.getEmployeeNum());
		delete.setAttendanceId(summaryform.getAttendanceId());
		delete.setAttendanceType(summaryform.getAttendanceType());
		
		dservice.deleteBridge(delete);
		
		model.addAttribute("employeeNum",summaryform.getEmployeeNum());
		
		return "delete-complete";
	}
	
}
