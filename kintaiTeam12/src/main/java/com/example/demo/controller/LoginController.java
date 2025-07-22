package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Login;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService service;
	
	@GetMapping("/")
	public String loginPage(@ModelAttribute LoginForm form,Model model) {
		return "login-page";
	}
	
	@PostMapping("login_start")
	public String loginStart(@ModelAttribute LoginForm form,Model model) {
		
		//System.out.println(form);
		
		Login l = new Login();
		
		l.setEmployeeNum(form.getEmployeeNum());
		l.setPass(form.getPass());
		
		boolean  flag = service.loginBridge(l);
		
		if(flag) {
			return "login-success";
		}else {
			model.addAttribute("message","社員番号またはパスワードが間違っています");
			return "login-page";
		}
		
	}
}
