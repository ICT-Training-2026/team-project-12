package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Edit;
import com.example.demo.entity.Summary;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EditConfirmRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	
	// 勤怠記録の編集
	public void updateByEdit(Summary summary) {
		
		String sql = "UPDATE ATTENDANCES "
				+ "SET ATTENDANCE_TYPE = ?, START_HOUR = ?, START_MINUTE = ?, FINISH_HOUR = ?, FINISH_MINUTE = ?, REST_TIME = ? "
				+ "WHERE ATTENDANCE_ID = ?;";
		
		System.out.println("EditConfirmRepository 勤怠区分確認：" + summary.getAttendanceType());
		System.out.println("EditConfirmRepository 勤怠ID確認：" + summary.getAttendanceId());
		
		
		jdbcTemplate.update(sql, summary.getAttendanceType(), summary.getStartHour(), summary.getStartMinute(), summary.getFinishHour(), summary.getFinishMinute(), summary.getRestTime(), summary.getAttendanceId());
		
	}
	
	// 年休の計算
	public void updateByPaidHoliday(Summary summary) {
		
		String sql = "UPDATE EMPLOYEES e "
				+ "JOIN ATTENDANCES a ON e.EMPLOYEE_NUM = a.EMPLOYEE_NUM "
				+ "SET e.PAID_HOLIDAYS = e.PAID_HOLIDAYS - 1 "
				+ "WHERE a.ATTENDANCE_TYPE = '年休' AND e.PAID_HOLIDAYS > 0 AND e.EMPLOYEE_NUM = ?;";
				
		
		jdbcTemplate.update(sql, summary.getEmployeeNum());
		
	}
	
	
	// 振休の計算
	public void updateBySubstitudeHoliday(Edit edit) {
		
		
	}
	
}
