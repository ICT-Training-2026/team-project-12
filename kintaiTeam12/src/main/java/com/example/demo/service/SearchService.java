package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.InputData;
import com.example.demo.repository.SearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchService {
	
	private final SearchRepository repository;
	
	public boolean authBridge(String empnum) {
		
		return repository.findByAuth(empnum);
	}
	
	public InputData NormalEmployeeInfo(String empnum) {
		
		InputData tmp;
		
		tmp = repository.findByEmployee(empnum);
		
		return tmp;
	}
}
