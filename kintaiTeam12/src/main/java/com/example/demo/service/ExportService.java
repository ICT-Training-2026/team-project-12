package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ExportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExportService {
	
	private final ExportRepository exrepository;
	
	public void exportBridge() {
		exrepository.makeCSV();
	}
}
