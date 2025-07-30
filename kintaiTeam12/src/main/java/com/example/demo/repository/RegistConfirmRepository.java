package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Regist;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegistConfirmRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	// 出勤のDB登録
	public void addByRegist(Regist regist) {
		
		String sql = "INSERT INTO ATTENDANCES (EMPLOYEE_NUM, YEAR, ATTENDANCE_TYPE, START_HOUR, START_MINUTE, FINISH_HOUR, FINISH_MINUTE, REST_TIME) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?)";
		
		System.out.println("RegistConfirmRepository" + regist.getAttendanceType());
		
		jdbcTemplate.update(sql, regist.getEmployeeNum(), regist.getYear(), regist.getAttendanceType(), regist.getStartHour(), regist.getStartMinute(), regist.getFinishHour(), regist.getFinishMinute(), regist.getRestTime());
		
		
		
	}
	
	// 年休の計算
	public void updateByPaidHoliday(Regist regist) {
		
		
		
		String sql = "UPDATE EMPLOYEES "
				+ "SET EMPLOYEES.PAID_HOLIDAYS = EMPLOYEES.PAID_HOLIDAYS - 1 "
				+ "WHERE EMPLOYEES.EMPLOYEE_NUM = ?;";
				
		
		jdbcTemplate.update(sql, regist.getEmployeeNum());
		
		System.out.println("年休確認社員番号：" + regist.getEmployeeNum());
		System.out.println("年休計算完了");
		
	}
	
	
	// 振出の計算
	public void updateByPlusSubstitudeHoliday(Regist regist) {
		
		String sql = "UPDATE EMPLOYEES "
				+ "SET EMPLOYEES.SUBSTITUDE_HOLIDAYS = EMPLOYEES.SUBSTITUDE_HOLIDAYS + 1 "
				+ "WHERE EMPLOYEES.EMPLOYEE_NUM = ?;";
				
		
		jdbcTemplate.update(sql, regist.getEmployeeNum());
		
		System.out.println("振出計算完了");
		
		
	}
	
	// 振休の計算
	public void updateByMinusSubstitudeHoliday(Regist regist) {
		
		String sql = "UPDATE EMPLOYEES "
				+ "SET EMPLOYEES.SUBSTITUDE_HOLIDAYS = EMPLOYEES.SUBSTITUDE_HOLIDAYS - 1 "
				+ "WHERE EMPLOYEES.EMPLOYEE_NUM = ?;";
				
		
		jdbcTemplate.update(sql, regist.getEmployeeNum());
		
	}
	
}
