package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Delete;
import com.example.demo.repository.DeleteConfirmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteConfirmService {

	private final DeleteConfirmRepository drepository;
	
	public void deleteBridge(Delete delete) {
		
		// 必要に応じて、関連データの更新を行う
	    if ("年休".equals(delete.getAttendanceType())) {
	    	
	        // 年休の削除に伴い、年休の残数を増やす
	    	drepository.updateByPaidHoliday(delete);
	    	
	    } 
	    
	    if ("振休".equals(delete.getAttendanceType())) {
	    	
	        // 振休の削除に伴い、振休の残数を増やす
	    	drepository.updateByPlusSubstitudeHoliday(delete);
	    	
	    }
	    
	    if ("振出".equals(delete.getAttendanceType())) {
	    	
	        // 振休の削除に伴い、振休の残数を増やす
	    	drepository.updateByMinusSubstitudeHoliday(delete);
	    	
	    }	    
	    

		
	 // 勤怠の削除
	 	drepository.deleteByAttend(delete);
	}
	
}