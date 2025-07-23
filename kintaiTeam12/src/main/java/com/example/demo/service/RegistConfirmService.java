package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Regist;
import com.example.demo.repository.RegistConfirmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistConfirmService {

	private final RegistConfirmRepository rrepository;
	
	public void registBridge(Regist regist) {
		
		rrepository.addByRegist(regist);
		
	}
	
}
