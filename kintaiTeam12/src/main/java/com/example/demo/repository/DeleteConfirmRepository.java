package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Delete;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeleteConfirmRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	// 勤怠記録の削除
	public void deleteByAttend(Delete delete) {
	    // 勤怠データを削除するSQL
	    String sql = "DELETE "
	    		+ "FROM ATTENDANCES "
	    		+ "WHERE ATTENDANCE_ID = ? ";

	    jdbcTemplate.update(sql,  delete.getAttendanceId());
	}

	// 年休削除後の更新
	public void updateByPaidHoliday(Delete delete) {
		String sql = "UPDATE EMPLOYEES "
				+ "SET EMPLOYEES.PAID_HOLIDAYS = EMPLOYEES.PAID_HOLIDAYS + 1 "
				+ "WHERE EMPLOYEES.EMPLOYEE_NUM = ?;";
		
	    jdbcTemplate.update(sql, delete.getEmployeeNum());
	}
			

	// 振休削除後の更新
	public void updateByPlusSubstitudeHoliday(Delete delete) {

		String sql = "UPDATE EMPLOYEES "
				+ "SET EMPLOYEES.SUBSTITUDE_HOLIDAYS = EMPLOYEES.SUBSTITUDE_HOLIDAYS + 1 "
				+ "WHERE EMPLOYEES.EMPLOYEE_NUM = ?;";
		
		jdbcTemplate.update(sql, delete.getEmployeeNum());
	  
	}
	
	// 振出削除後の更新
		public void updateByMinusSubstitudeHoliday(Delete delete) {

			String sql = "UPDATE EMPLOYEES "
					+ "SET EMPLOYEES.SUBSTITUDE_HOLIDAYS = EMPLOYEES.SUBSTITUDE_HOLIDAYS - 1 "
					+ "WHERE EMPLOYEES.EMPLOYEE_NUM = ?;";
		  
			jdbcTemplate.update(sql, delete.getEmployeeNum());
	}
}
