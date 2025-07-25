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
	    		+ "WHERE EMPLOYEE_NUM = ? "
	    		+ "AND YEAR = ? "
	    		+ "AND ATTENDANCE_TYPE = ? "
	    		+ "AND START_HOUR = ? "
	    		+ "AND START_MINUTE = ? "
	    		+ "AND FINISH_HOUR = ? "
	    		+ "AND FINISH_MINUTE = ?";

	    jdbcTemplate.update(sql, delete.getEmployeeNum()
	    		, delete.getYear(), delete.getAttendanceType()
	    		, delete.getStartHour(), delete.getStartMinute()
	    		, delete.getFinishHour(), delete.getFinishMinute());
	}

	// 年休削除後の更新
	public void updateByPaidHoliday(Delete delete) {
		String sql = "UPDATE EMPLOYEES e "
				+ "JOIN ATTENDANCES a ON e.EMPLOYEE_NUM = a.EMPLOYEE_NUM "
				+ "SET e.PAID_HOLIDAYS = e.PAID_HOLIDAYS + 1 "
				+ "WHERE a.ATTENDANCE_TYPE = '年休' AND e.PAID_HOLIDAYS > 0 AND e.EMPLOYEE_NUM = ?;";
		
	    jdbcTemplate.update(sql, delete.getEmployeeNum());
	}
			

	// 振休削除後の更新
	public void updateByPlusSubstitudeHoliday(Delete delete) {

		String sql = "UPDATE EMPLOYEES e "
				+ "JOIN ATTENDANCES a ON e.EMPLOYEE_NUM = a.EMPLOYEE_NUM "
				+ "SET e.SUBSTITUDE_HOLIDAYS = e.SUBSTITUDE_HOLIDAYS + 1 "
				+ "WHERE a.ATTENDANCE_TYPE = '振出' AND e.EMPLOYEE_NUM = ?;";
	  
	}
	
	// 振出削除後の更新
		public void updateByMinusSubstitudeHoliday(Delete delete) {

			String sql = "UPDATE EMPLOYEES e "
					+ "JOIN ATTENDANCES a ON e.EMPLOYEE_NUM = a.EMPLOYEE_NUM "
					+ "SET e.SUBSTITUDE_HOLIDAYS = e.SUBSTITUDE_HOLIDAYS - 1 "
					+ "WHERE a.ATTENDANCE_TYPE = '振出' AND e.EMPLOYEE_NUM = ?;";
		  
	}
}
