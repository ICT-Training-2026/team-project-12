package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Delete;
import com.example.demo.form.AttendForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.DeleteConfirmService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteConfirmController {
	
	private final DeleteConfirmService dservice;
	
	
	// 編集削除を選ぶ画面へ戻る
	@PostMapping("return_edit_delete2")
	public String returnEditDeletePage2() {
		
		return "edit-delete";
	}
	
    //	削除完了画面にいく
	@PostMapping("delete_complete_page")
	public String deleteCompletePage(@ModelAttribute MyForm myform, @ModelAttribute AttendForm attendform, Model model) {
		
		Delete delete = new Delete();
		
		delete.setEmployeeNum(attendform.getEmployeeNum());
		delete.setYear(attendform.getYear());
		delete.setAttendanceType(attendform.getAttendanceType());
		delete.setStartHour(attendform.getStartHour());
		delete.setStartMinute(attendform.getStartMinute());
		delete.setFinishHour(attendform.getFinishHour());
		delete.setFinishMinute(attendform.getFinishMinute());
		delete.setRestTime(attendform.getRestTime());
		
		//データの削除
		dservice.deleteBridge(delete);
		
		model.addAttribute("employeenum",attendform.getEmployeeNum());		
		
		return "delete-complete";
	}
	
}
