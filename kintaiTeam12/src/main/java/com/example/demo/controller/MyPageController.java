package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AttendForm;
import com.example.demo.form.MyForm;

@Controller
public class MyPageController {
	
	// 認証画面へ戻る
	@PostMapping("return_verified_page")
	public String returnVerifiedPage() {
		
		return "login-success";
	}
	
	// 勤怠登録画面へ
	@PostMapping("regist_attend")
	public String registPage(@ModelAttribute MyForm myform, @ModelAttribute AttendForm registform, Model modelO) {
		
		return "regist-attend";
	}
	
	// 編集・削除画面へ
	@PostMapping("edit_delete")
	public String editDeletePage() {
		
		return "edit-delete";
	}
	
	// 検索画面へ
	@PostMapping("search_normal")
	public String normalSearchPage() {
		
//		if (true) {
//			return "search-special";
//		} else {
//			return "search-normal";
//		}
		
		// ↓仮の返り値を設定した
		return "search-normal";
		
	}
	
	

}
