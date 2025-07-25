package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Delete;
import com.example.demo.form.SummaryForm;
import com.example.demo.service.DeleteConfirmService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteConfirmController {
	
	private final DeleteConfirmService dservice;
	
	
//	// 編集削除を選ぶ画面へ戻る
//	@PostMapping("return_edit_delete2")
//	public String returnEditDeletePage2() {
//		
//		return "edit-delete";
//	}
	
    //	削除完了画面にいく
	@PostMapping("delete_complete_page")
	public String deleteCompletePage(@ModelAttribute SummaryForm summaryform) {
		
		//System.out.println(summaryform);
		
		Delete delete = new Delete();
		
		delete.setEmployeeNum(summaryform.getEmployeeNum());
		delete.setAttendanceId(summaryform.getAttendanceId());
		delete.setAttendanceType(summaryform.getAttendanceType());
		
		dservice.deleteBridge(delete);
		
		return "delete-complete";
	}
	
}
