package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.InputData;
import com.example.demo.form.AttendForm;
import com.example.demo.form.InputDataForm;
import com.example.demo.form.MyForm;
import com.example.demo.service.SearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {
	
	private final SearchService service;
	
	// 認証画面へ戻る
	@PostMapping("return_verified_page")
	public String returnVerifiedPage() {
		
		
		
		return "login-success";
	}
	
	// 勤怠登録画面へ
	@PostMapping("/regist_attend")
	public String registPage(@ModelAttribute MyForm myform, @ModelAttribute AttendForm registform, Model model) {
		
		model.addAttribute("employeenum",myform.getEmployeeNum());
		
		return "regist-attend";
	}
	
	// 編集・削除画面へ
	@PostMapping("/edit_delete")
	public String editDeletePage(@ModelAttribute MyForm myform, @ModelAttribute AttendForm registform, Model model) {
		
		return "edit-delete";
	}
	
	// 検索画面へ
	@PostMapping("/search")
	public String searchPage(@ModelAttribute MyForm myform, @ModelAttribute InputDataForm inputform, Model model) {
		
		boolean result = service.authBridge(myform.getEmployeeNum());
		model.addAttribute("employeenum",myform.getEmployeeNum());
		
		if(result) {
			return "search-special";
		}else {
			
			InputData data = new InputData();
			
			data = service.NormalEmployeeInfo(myform.getEmployeeNum());
			
			System.out.println(data);
			
			model.addAttribute("empName",data.getEmpName());
			model.addAttribute("empNum",myform.getEmployeeNum());
			model.addAttribute("composeId",data.getComposeId());
			
			inputform.setEmployeeNum(myform.getEmployeeNum());
			inputform.setEmpName(data.getEmpName());
			inputform.setEmpNum(myform.getEmployeeNum());
			inputform.setComposeId(data.getComposeId());
			
			return "search-normal";
		}
	}
}
