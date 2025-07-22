package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Login;
import com.example.demo.form.EmployeeNumForm;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService service;
	
	@GetMapping("/")
	public String loginPage(@ModelAttribute LoginForm loginform,Model model) {
		return "login-page";
	}
	
	@PostMapping("login_start")
	public String loginStart(@ModelAttribute LoginForm logform,@ModelAttribute EmployeeNumForm empform ,Model model) {
		
		Login l = new Login();
		
		l.setEmployeeNum(logform.getEmployeeNum());
		l.setPass(logform.getPass());
		
		boolean  flag = service.loginBridge(l);
		
		if(flag) {
			empform.setEmployeeNum(logform.getEmployeeNum());
			return "login-success";
		}else {
			model.addAttribute("message","社員番号またはパスワードが間違っています");
			return "login-page";
		}
		
	}
}
