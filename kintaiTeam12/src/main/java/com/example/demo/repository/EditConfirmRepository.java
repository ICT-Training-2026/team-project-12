package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Edit;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EditConfirmRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	// DBから見つける
//		public List<Edit> findBySameday(List<edit>) {
//			
//			String sql = "INSERT INTO ATTENDANCES (EMPLOYEE_NUM, YEAR, ATTENDANCE_TYPE, START_HOUR, START_MINUTE, FINISH_HOUR, FINISH_MINUTE, REST_TIME) VALUES "
//					+ "(?, ?, ?, ?, ?, ?, ?, ?)";
//			
//			System.out.println("RegistConfirmRepository" + edit.getAttendanceType());
//			
//			jdbcTemplate.update(sql, edit.getEmployeeNum(), edit.getYear(), edit.getAttendanceType(), edit.getStartHour(), edit.getStartMinute(), edit.getFinishHour(), edit.getFinishMinute(), edit.getRestTime());
//			
//		}
	
	// 勤怠記録の編集
	public void updateByEdit(Edit edit) {
		
		String sql = "INSERT INTO ATTENDANCES (EMPLOYEE_NUM, YEAR, ATTENDANCE_TYPE, START_HOUR, START_MINUTE, FINISH_HOUR, FINISH_MINUTE, REST_TIME) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?)";
		
		System.out.println("EditConfirmRepository" + edit.getAttendanceType());
		
		jdbcTemplate.update(sql, edit.getEmployeeNum(), edit.getYear(), edit.getAttendanceType(), edit.getStartHour(), edit.getStartMinute(), edit.getFinishHour(), edit.getFinishMinute(), edit.getRestTime());
		
	}
	
	// 年休の計算
	public void updateByPaidHoliday(Edit edit) {
		
		String sql = "UPDATE EMPLOYEES e "
				+ "JOIN ATTENDANCES a ON e.EMPLOYEE_NUM = a.EMPLOYEE_NUM "
				+ "SET e.PAID_HOLIDAYS = e.PAID_HOLIDAYS - 1 "
				+ "WHERE a.ATTENDANCE_TYPE = '年休' AND e.PAID_HOLIDAYS > 0 AND e.EMPLOYEE_NUM = ?;";
				
		
		jdbcTemplate.update(sql, edit.getEmployeeNum());
		
	}
	
	
	// 振休の計算
	public void updateBySubstitudeHoliday(Edit edit) {
		
		
	}
	
}
