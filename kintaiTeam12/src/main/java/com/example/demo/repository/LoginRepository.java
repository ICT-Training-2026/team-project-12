package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Login;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoginRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public boolean findByEmployee(Login tmp) {
		
		String sql = "SELECT EXISTS (" + 
				 " SELECT 1" +
				 " FROM employees" + 
				 " WHERE EMPLOYEE_NUM = ? AND PASS = ?" + 
				 " )";
		
		Integer exits = jdbcTemplate.queryForObject(sql, Integer.class,tmp.getEmployeeNum(),tmp.getPass() );
		
		if(exits==1) {
			return true;
		}else {
			return false;
		}
	}
	
}
