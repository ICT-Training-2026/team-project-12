package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AttendForm;
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
	
//	@PostMapping("edit")
//	public String editPage(@ModelAttribute InputDataForm inputdataform, @ModelAttribute AttendForm attendform, Model model) {
//		
//
//		
//		return "edit";
//	}
	
	
	@PostMapping("delete")
	public String deletePage(@ModelAttribute MyForm myform,@ModelAttribute AttendForm attendform, Model model){
		

		return "delete";
	}
}
	

