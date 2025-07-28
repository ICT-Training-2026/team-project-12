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

		if ("年休".equals(summary.getAttendanceType())) {

			System.out.println("if年休：起動確認");

			ecrepository.updateByPaidHoliday(summary);
		}

		// 振出の計算
		if ("振出".equals(summary.getAttendanceType())) {

			System.out.println("if振出：起動確認");

			ecrepository.updateByPlusSubstitudeHoliday(summary);

		}

		// 振休の計算
		if ("振休".equals(summary.getAttendanceType())) {

			System.out.println("if振休：起動確認");

			ecrepository.updateByMinusSubstitudeHoliday(summary);

		}

		// 勤怠時間の編集登録
		ecrepository.updateByEdit(summary);
		
		
	}
	
}
