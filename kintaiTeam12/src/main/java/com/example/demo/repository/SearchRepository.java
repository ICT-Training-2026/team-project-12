package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SearchRepository {
	private final JdbcTemplate jdbcTemplate;
	
	public boolean findByAuth(String empnum) { 
		
		String sql ="SELECT SEARCH_AUTHORITY "+
					"FROM employees " +
					"WHERE EMPLOYEE_NUM = ?";
		
		
		Integer exits = jdbcTemplate.queryForObject(sql,Integer.class,empnum);
		
		
		if(exits==1) {
			return true;
		}else{
			return false;
		}
	}

}
