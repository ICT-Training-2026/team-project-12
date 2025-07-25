package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Regist;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegistExistRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public boolean findByAttendance(Regist regist) {
		
		String sql = "SELECT EXISTS (" + 
				 " SELECT 1" +
				 " FROM attendances" + 
				 " WHERE EMPLOYEE_NUM = ? AND YEAR = ?" + 
				 " );";
		
		System.out.println("リポジトリ起動確認");
		
		Integer exits = jdbcTemplate.queryForObject(sql, Integer.class,regist.getEmployeeNum(), Integer.class,regist.getYear());
		
		
		
		if(exits==1) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
