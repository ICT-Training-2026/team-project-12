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
		
		
		// 編集前の勤怠区分を取ってくる
		
		String beforeAttendanceType = ecrepository.selectAttendanceType(summary.getAttendanceId());
		
		System.out.println("勤怠区分のbefore：" + beforeAttendanceType);
		
		
		// 振休を変えるとき
		if ("振休".equals(beforeAttendanceType)) {
			
			System.out.println("if振休前：起動確認");

			ecrepository.updateByPlusSubstitudeHoliday(summary);
			
		}
		
		
		// 年休を変えるとき
		if ("年休".equals(beforeAttendanceType)) {
			
			System.out.println("if年休前：起動確認");

			ecrepository.updateByPlusPaidHoliday(summary);
			
		}
		
		// 振出を変えるとき
		if ("振出".equals(beforeAttendanceType)) {
			
			System.out.println("if振出前：起動確認");

			ecrepository.updateByMinusSubstitudeHoliday(summary);
			
		}
		
		
		
		
		// 編集後の年休の計算
		if ("年休".equals(summary.getAttendanceType())) {

			System.out.println("if年休：起動確認");

			ecrepository.updateByMinusPaidHoliday(summary);
		}

		// 編集後の振出の計算
		if ("振出".equals(summary.getAttendanceType())) {

			System.out.println("if振出：起動確認");

			ecrepository.updateByPlusSubstitudeHoliday(summary);

		}

		// 編集後の振休の計算
		if ("振休".equals(summary.getAttendanceType())) {

			System.out.println("if振休：起動確認");

			ecrepository.updateByMinusSubstitudeHoliday(summary);

		}

		// 勤怠時間の編集登録
		ecrepository.updateByEdit(summary);
		
		
	}
	
}
