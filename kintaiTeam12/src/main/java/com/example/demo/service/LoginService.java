package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Login;
import com.example.demo.repository.LoginRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final LoginRepository repository;
	
	public boolean loginBridge(Login tmp) {
		
		boolean flag = repository.findByEmployee(tmp);
		
		return flag;
	}
}
