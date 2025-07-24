package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.InputData;
import com.example.demo.entity.Summary;
import com.example.demo.repository.EditDeleteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditDeleteService {
	
	private final EditDeleteRepository edrepository;
	
	public List<Summary> editDelteBridge(InputData data) {
		
		List<Summary> tmp = edrepository.findByMine(data);
		
		return tmp;
	}
}
