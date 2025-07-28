package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MyPage;
import com.example.demo.form.MyForm;
import com.example.demo.form.SummaryForm;
import com.example.demo.service.NameService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditDeleteController {
	
	private final NameService nservice;
	
	// マイページへ戻る
	@PostMapping("edit_delete_return_mypage")
	public String returnMyPage(@ModelAttribute MyForm myform, @ModelAttribute EmployeeNumForm empform Model model) {		
		
		MyPage tmp = new MyPage();
		
		System.out.println(myform);
		
		tmp = nservice.MyReference(myform.getEmployeeNum());
		
		model.addAttribute("employeenum",myform.getEmployeeNum());
		model.addAttribute("name",tmp.getName());
		model.addAttribute("hours",tmp.getHours());
		
		return "my-page";
	}
	
//	@PostMapping("edit")
//	public String editPage(@ModelAttribute InputDataForm inputdataform, @ModelAttribute AttendForm attendform, Model model) {
//		
//
//		
//		return "edit";
//	}
	
	
	@PostMapping("delete")
	public String deletePage(@ModelAttribute SummaryForm summaryform, Model model,@ModelAttribute MyForm myform){
		
		System.out.println(summaryform);
		
		String formattedStartMinute = String.format("%02d", summaryform.getStartMinute());
	    String formattedFinishMinute = String.format("%02d", summaryform.getFinishMinute());

	    model.addAttribute("year", summaryform.getDate());
	    model.addAttribute("attendanceType", summaryform.getAttendanceType());
	    model.addAttribute("startHour", summaryform.getStartHour());
	    model.addAttribute("startMinute", formattedStartMinute);
	    model.addAttribute("finishHour", summaryform.getFinishHour()); // 修正: finishHourを取得
	    model.addAttribute("finishMinute", formattedFinishMinute);
	    model.addAttribute("restTime", summaryform.getRestTime());

	    // hidden fields
	    model.addAttribute("summaryForm", summaryform); // ここでsummaryFormを追加
		
		
		return "delete";
	}
	
	
	@PostMapping("edit")
	public String editPage(@ModelAttribute SummaryForm summaryform, Model model, @ModelAttribute MyForm myform) {

		System.out.println(summaryform);

		String formattedStartMinute = String.format("%02d", summaryform.getStartMinute());
		String formattedFinishMinute = String.format("%02d", summaryform.getFinishMinute());

		model.addAttribute("date", summaryform.getDate());
		model.addAttribute("attendanceId", summaryform.getAttendanceId());
		model.addAttribute("empName", summaryform.getEmpName());
		model.addAttribute("attendanceType", summaryform.getAttendanceType());
		model.addAttribute("startHour", summaryform.getStartHour());
		model.addAttribute("startMinute", formattedStartMinute);
		model.addAttribute("finishHour", summaryform.getFinishHour()); // 修正: finishHourを取得
		model.addAttribute("finishMinute", formattedFinishMinute);
		model.addAttribute("restTime", summaryform.getRestTime());

		// hidden fields
		model.addAttribute("summaryForm", summaryform); // ここでsummaryFormを追加
		
		System.out.println("編集起動確認：" + summaryform);

		return "edit";
	}
	
	
}
	

