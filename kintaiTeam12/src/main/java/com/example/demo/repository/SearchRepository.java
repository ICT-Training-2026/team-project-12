package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.InputData;

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
	
	public InputData findByEmployee(String empnum) {
		
		
		String sql = "SELECT "
		        + "    E.NAME_JP,"
		        + "    D.DEPARTMENT_NAME,"
		        + "    V.DEVISION_NAME,"
		        + "    CONCAT(D.DEPARTMENT_NAME, ' ', V.DEVISION_NAME) AS 部署名 "
		        + "FROM "
		        + "    EMPLOYEES E "
		        + "JOIN "
		        + "    COMPOSE C ON E.COMPOSE_ID = C.COMPOSE_ID "
		        + "JOIN "
		        + "    DEPARTMENTS D ON C.DEPARTMENT_CODE = D.DEPARTMENT_CODE "
		        + "JOIN "
		        + "    DEVISIONS V ON C.DEVISION_CODE = V.DEVISION_CODE "
		        + "WHERE "
		        + "    EMPLOYEE_NUM = ?;";
		
		List<Map<String, Object>> ds = jdbcTemplate.queryForList(sql,empnum);
		
		InputData tmp = new InputData();
		
		for(Map<String, Object> d : ds) {
			tmp.setComposeId((String)d.get("部署名"));
			tmp.setEmpName((String)d.get("NAME_JP"));
		}
	
		return tmp;
	}

}
