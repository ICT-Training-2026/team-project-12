package com.example.demo.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.InputData;
import com.example.demo.entity.Summary;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EditDeleteRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Summary> findByMine(InputData data) {
        String sql = "SELECT "
                + "a.DATE AS 日付, "
                + "a.EMPLOYEE_NUM AS 社員番号, "
                + "a.ATTENDANCE_TYPE AS 出勤区分, "
                + "CONCAT(a.START_HOUR, ':', LPAD(a.START_MINUTE, 2, '0')) AS 出勤時間, "
                + "CONCAT(a.FINISH_HOUR, ':', LPAD(a.FINISH_MINUTE, 2, '0')) AS 退勤時間, "
                + "a.REST_TIME AS 休憩分 "
                + "FROM ATTENDANCES a "
                + "WHERE a.EMPLOYEE_NUM = ? "
                + "AND a.DATE BETWEEN ? AND ? "
                + "ORDER BY a.DATE";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

        List<Summary> summaries = new ArrayList<>();
        for (Map<String, Object> row : results) {
            Summary summary = new Summary();
            summary.setDate((Date) row.get("日付"));
            summary.setEmployeeNum((String) row.get("社員番号"));
            summary.setAttendanceType((String) row.get("出勤区分"));
            summary.setStartTime((String) row.get("出勤時間"));
            summary.setFinishTime((String) row.get("退勤時間"));
            summary.setRestTime((int) row.get("休憩分"));
            summaries.add(summary);
        }

        return summaries;
    }
}
//public class EditDeleteRepository {
//
//	private final JdbcTemplate jdbcTemplate;
//	
//	public List<Summary> findByMine(String employeeNum, Date startDate, Date endDate) {
//	    String sql = "SELECT "
//	            + "a.DATE AS 日付, "
//	            + "a.ATTENDANCE_TYPE AS 出勤区分, "
//	            + "CONCAT(a.START_HOUR, ':', LPAD(a.START_MINUTE, 2, '0')) AS 出勤時間, "
//	            + "CONCAT(a.FINISH_HOUR, ':', LPAD(a.FINISH_MINUTE, 2, '0')) AS 退勤時間, "
//	            + "a.REST_TIME AS 休憩分 "
//	            + "FROM ATTENDANCES a "
//	            + "WHERE a.EMPLOYEE_NUM = ? "
//	            + "AND a.DATE BETWEEN ? AND ? "
//	            + "ORDER BY a.DATE";
//
//	    List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, employeeNum, startDate, endDate);
//
//	    List<Summary> all_result = new ArrayList<>();
//	    for (Map<String, Object> row : results) {
//	        Summary summary = new Summary();
//	        summary.setDate((Date) row.get("日付"));
//	        summary.setAttendanceType((String) row.get("出勤区分"));
//	        summary.setStartTime((String) row.get("出勤時間"));
//	        summary.setFinishTime((String) row.get("退勤時間"));
//	        summary.setRestTime((int) row.get("休憩分"));
//	        all_result.add(summary);
//	    }
//
//	    return all_result;
//	}
//
//}