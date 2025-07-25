package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PersonalRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public String findByCompose(String empnum) {
		
		String compose = "test";
		
		String sql = "SELECT COMPOSE_ID " +
				 	 "FROM employees " +
				 	 "WHERE EMPLOYEE_NUM = ? ";
	
		compose = jdbcTemplate.queryForObject(sql,String.class,empnum);
		
		return compose;
	}
}
