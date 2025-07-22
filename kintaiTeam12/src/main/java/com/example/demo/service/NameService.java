package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MyPage;
import com.example.demo.repository.NameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NameService {
	
	
	private final NameRepository repository;
	
	public MyPage MyReference(String empnum) {
		
		
		MyPage tmp = new MyPage();
		
		tmp.setName(repository.findByName(empnum));
		tmp.setHours(repository.sumByHours());
		
		return tmp;
	}
}
