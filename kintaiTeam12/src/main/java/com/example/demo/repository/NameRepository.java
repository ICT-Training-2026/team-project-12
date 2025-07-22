package com.example.demo.repository;

import java.time.LocalDate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NameRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public String findByName(String empnum) {
		
		
		String sql = "SELECT NAME_JP " +
					 "FROM employees " +
					 "WHERE EMPLOYEE_NUM = ?";
		
		String name = jdbcTemplate.queryForObject(sql,String.class,empnum);
		
		return name;
	}
	
	public String sumByHours(String empnum) {
		
		String total = "0";
		
		
		LocalDate currentDate = LocalDate.now();
		
		int year = currentDate.getYear();
		int month = currentDate.getMonthValue();
		
		LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
		LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
		
		String sql = "SELECT "
	            + "    SUM((FINISH_HOUR * 60 + FINISH_MINUTE) - (START_HOUR * 60 + START_MINUTE) - REST_TIME) / 60 AS WORK_HOURS "
	            + "FROM "
	            + "    ATTENDANCES "
	            + "WHERE "
	            + "    EMPLOYEE_NUM = ? "
	            + "    AND ATTENDANCE_TYPE IN (?, ?, ?) " 
	            + "    AND YEAR BETWEEN ? AND ? "
	            + "GROUP BY "
	            + "    EMPLOYEE_NUM ";
		
	    total = jdbcTemplate.queryForObject(sql, String.class, empnum, "出勤", "年休", "振出", firstDayOfMonth, lastDayOfMonth);
		
	    int dotIndex = total.indexOf(".");
	    total = (dotIndex == -1) ? total : total.substring(0, dotIndex);
		return total;
	}
}
