package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Summary;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EditConfirmRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	// 前の勤怠区分を取ってくる
	public String selectAttendanceType(int attendanceId) {
		
		String sql = "SELECT ATTENDANCE_TYPE FROM ATTENDANCES WHERE ATTENDANCE_ID = ?";
		
		String attendanceType = jdbcTemplate.queryForObject(sql, String.class, attendanceId);
		
		
		return attendanceType;
	}
	
	
	
	// 勤怠記録の編集
	public void updateByEdit(Summary summary) {
		
		String sql = "UPDATE ATTENDANCES "
				+ "SET ATTENDANCE_TYPE = ?, START_HOUR = ?, START_MINUTE = ?, FINISH_HOUR = ?, FINISH_MINUTE = ?, REST_TIME = ? "
				+ "WHERE ATTENDANCE_ID = ?;";
		
		System.out.println("EditConfirmRepository 勤怠区分確認：" + summary.getAttendanceType());
		System.out.println("EditConfirmRepository 勤怠ID確認：" + summary.getAttendanceId());
		
		
		jdbcTemplate.update(sql, summary.getAttendanceType(), summary.getStartHour(), summary.getStartMinute(), summary.getFinishHour(), summary.getFinishMinute(), summary.getRestTime(), summary.getAttendanceId());
		
	}
	
	// 年休を減らす計算
	public void updateByMinusPaidHoliday(Summary summary) {

		String sql = "UPDATE EMPLOYEES "
				+ "SET EMPLOYEES.PAID_HOLIDAYS = EMPLOYEES.PAID_HOLIDAYS - 1 "
				+ "WHERE EMPLOYEES.EMPLOYEE_NUM = ?;";

		jdbcTemplate.update(sql, summary.getEmployeeNum());

	}
	
	
	// 年休を増やす計算
	public void updateByPlusPaidHoliday(Summary summary) {

		String sql = "UPDATE EMPLOYEES "
				+ "SET EMPLOYEES.PAID_HOLIDAYS = EMPLOYEES.PAID_HOLIDAYS + 1 "
				+ "WHERE EMPLOYEES.EMPLOYEE_NUM = ?;";

		jdbcTemplate.update(sql, summary.getEmployeeNum());

	}
	
	

	// 振出の計算
	public void updateByPlusSubstitudeHoliday(Summary summary) {

		String sql = "UPDATE EMPLOYEES "
				+ "SET EMPLOYEES.SUBSTITUDE_HOLIDAYS = EMPLOYEES.SUBSTITUDE_HOLIDAYS + 1 "
				+ "WHERE EMPLOYEES.EMPLOYEE_NUM = ?;";

		jdbcTemplate.update(sql, summary.getEmployeeNum());

	}
	

	// 振休の計算
	public void updateByMinusSubstitudeHoliday(Summary summary) {
		
		String sql = "UPDATE EMPLOYEES "
				+ "SET EMPLOYEES.SUBSTITUDE_HOLIDAYS = EMPLOYEES.SUBSTITUDE_HOLIDAYS - 1 "
				+ "WHERE EMPLOYEES.EMPLOYEE_NUM = ?;";
				
		
		jdbcTemplate.update(sql, summary.getEmployeeNum());
		
		
	}
	
}
