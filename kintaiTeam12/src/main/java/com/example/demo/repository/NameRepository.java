package com.example.demo.repository;

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
	
	public int sumByHours() {
		
		int total = 0;
		
		return total;
	}
}
