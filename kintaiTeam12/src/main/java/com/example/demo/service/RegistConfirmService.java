package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Regist;
import com.example.demo.repository.RegistConfirmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistConfirmService {

	private final RegistConfirmRepository rrepository;
	
	public void registBridge(Regist regist) {
		
		System.out.println("RegistConfirmService" + regist);
		
		
		// 年休の計算
		// 労働時間を固定
		if("年休".equals(regist.getAttendanceType())) {
			
			System.out.println("if年休：起動確認");
			
			
			rrepository.updateByPaidHoliday(regist);
		}
		
		
		// 振出の計算
		if("振出".equals(regist.getAttendanceType())) {
			
			System.out.println("if振出：起動確認");
			
			rrepository.updateByPlusSubstitudeHoliday(regist);
			
		}
		
		
		// 振休の計算
		if ("振休".equals(regist.getAttendanceType())) {
			
			System.out.println("if振休：起動確認");
			
			rrepository.updateByMinusSubstitudeHoliday(regist);

		}
		
		
		// 勤怠時間の登録
		rrepository.addByRegist(regist);
		
	}
	
}
