//package com.example.demo.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//import com.example.demo.form.EmployeeNumForm;
//
//@Controller
//public class HeaderController {
//
//	@GetMapping("/my_page")
//	public String moveMypage(@ModelAttribute EmployeeNumForm form, Model model) {
//		
//		System.out.println("form確認：" + form);
//		
//		model.addAttribute("employeeNum", form.getEmployeeNum());
//		 model.addAttribute("employeeNum", form.getEmployeeNum());
//		
//		
//		return "my-page";
//	}
//}
