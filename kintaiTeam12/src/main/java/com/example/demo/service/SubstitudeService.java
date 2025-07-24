package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.SubstitudeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubstitudeService {
	
	private final SubstitudeRepository srepository;
	
	public int substitudeBridge(String empNum) {
		
		int countSubstitudeHoliday = srepository.findBySubstitudeHoliday(empNum);
		
		return countSubstitudeHoliday;
	}
	
	
}
