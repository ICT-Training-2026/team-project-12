package com.example.demo.repository;

import java.sql.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HolidayRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public boolean findByHoliday(Date holiday) {
		
		
		String sql = "SELECT EXISTS (" + 
				 " SELECT 1" +
				 " FROM calendar" + 
				 " WHERE CALENDAR_ID = ? AND VACATION = 1" + 
				 " )";
		
		Integer exits = jdbcTemplate.queryForObject(sql, Integer.class,holiday);
		
		if(exits==1) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public int findByPaidHoliday(String empNum) {
		
		String sql = "SELECT PAID_HOLIDAYS "
				+ "FROM EMPLOYEES "
				+ "WHERE EMPLOYEE_NUM = ?;";
		
		int countPaidHoliday = jdbcTemplate.queryForObject(sql, Integer.class, empNum);
		
		return countPaidHoliday;
	}


}
