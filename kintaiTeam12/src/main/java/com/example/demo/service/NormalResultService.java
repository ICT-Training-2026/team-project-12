package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.InputData;
import com.example.demo.entity.Result;
import com.example.demo.repository.NormalResultRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NormalResultService {
	private final NormalResultRepository srepository;
	
	public List<Result> specialSearchBridge(InputData data) {
		
		List<Result> tmp = srepository.findByAttends(data);
		
		return tmp;
	}
}
