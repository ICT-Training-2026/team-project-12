package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SubstitudeRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public int findBySubstitudeHoliday(String empNum) {
		
		String sql = "SELECT SUBSTITUDE_HOLIDAYS "
				+ "FROM EMPLOYEES "
				+ "WHERE EMPLOYEE_NUM = ?;";
		
		int countPaidHoliday = jdbcTemplate.queryForObject(sql, Integer.class, empNum);
		
		return countPaidHoliday;
		
	}
	
	
}
