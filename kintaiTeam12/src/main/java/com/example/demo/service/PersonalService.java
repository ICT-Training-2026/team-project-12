package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.PersonalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalService {
	
	private final PersonalRepository repository;
	
	public String personalReference(String empnum) {
		
		String tmp = "test";
		
		System.out.println("Service personalpage");
		
		tmp = repository.findByCompose(empnum);
		
		return tmp;
	}
}
