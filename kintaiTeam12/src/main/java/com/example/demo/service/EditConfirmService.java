package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Edit;
import com.example.demo.repository.EditConfirmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditConfirmService {

	private final EditConfirmRepository erepository;
	
	public void editBridge(Edit edit) {
		
		System.out.println("EditConfirmService" + edit);
		
		
		// 年休の計算
		// 労働時間を固定
		if("年休".equals(edit.getAttendanceType())) {
			
			System.out.println("if年休：起動確認");
			
			
			erepository.updateByPaidHoliday(edit);
		}
		
		
		
		// 勤怠時間の登録
		erepository.updateByEdit(edit);
		
	}
	
}
