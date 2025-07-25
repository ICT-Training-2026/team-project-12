package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Regist;
import com.example.demo.repository.RegistExistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistExistService {
	
	private final RegistExistRepository rerepository;
	
	public boolean attendanceBridge(Regist regist) {
		
		boolean attendFlag = rerepository.findByAttendance(regist);
		
		return attendFlag;
	}
	
	
}
