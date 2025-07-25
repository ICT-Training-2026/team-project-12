package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Summary;
import com.example.demo.repository.EditDeleteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditDeleteService {
	
	private final EditDeleteRepository edrepository;
	
	public List<Summary> monthAttends(String empnum) {
		
		List<Summary> tmp = edrepository.findByAttends(empnum);
		
		return tmp;
	}
}
