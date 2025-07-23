package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Regist;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegistConfirmRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public void addByRegist(Regist regist) {
		
		String sql = "INSERT INTO ATTENDANCES (EMPLOYEE_NUM, YEAR, ATTENDANCE_TYPE, START_HOUR, START_MINUTE, FINISH_HOUR, FINISH_MINUTE, REST_TIME) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, regist.getEmployeeNum(), regist.getYear(), regist.getAttendanceType(), regist.getStartHour(), regist.getStartMinute(), regist.getFinishHour(), regist.getFinishMinute(), regist.getRestTime());
		
		System.out.println("RegistConfirm" + regist);
		
	}
	
	public void updateByPaidHoliday(Regist regist) {
		
		
		
	}
	
	public void updateBySubstitudeHoliday(Regist regist) {
		
		
		
	}
	
}
