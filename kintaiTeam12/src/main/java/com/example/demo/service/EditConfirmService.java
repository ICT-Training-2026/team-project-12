package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Summary;
import com.example.demo.repository.EditConfirmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditConfirmService {

	private final EditConfirmRepository ecrepository;
	
	public void editBridge(Summary summary) {
		
		System.out.println("EditConfirmService 起動確認：" + summary);
		
		
		
		
		
		
		
		
		
		// 勤怠時間の編集登録
		ecrepository.updateByEdit(summary);
		
		
	}
	
}
