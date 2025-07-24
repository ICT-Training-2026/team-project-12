package com.example.demo.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.InputData;
import com.example.demo.entity.Result;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NormalResultRepository {
	private final JdbcTemplate jdbcTemplate;
	
	 public List<Result> findByAttends(InputData data) {
		//Regist[] tmp = {'test'};
		
		System.out.println("NormalResultRepository : " + data);
		
		String sql = "SELECT "
		        + "a.YEAR AS 日付, "
		        + "e.NAME_JP AS 社員名, "
		        + "CONCAT(d.DEPARTMENT_NAME, ' ', v.DEVISION_NAME) AS 部署, "
		        + "a.ATTENDANCE_TYPE AS 出勤区分, "
		        + "CONCAT(a.START_HOUR, ':', LPAD(a.START_MINUTE, 2, '0')) AS 出勤時間, "
		        + "CONCAT(a.FINISH_HOUR, ':', LPAD(a.FINISH_MINUTE, 2, '0')) AS 退勤時間, "
		        + "a.REST_TIME AS 休憩分 "
		        + "FROM ATTENDANCES a "
		        + "JOIN EMPLOYEES e ON a.EMPLOYEE_NUM = e.EMPLOYEE_NUM "
		        + "JOIN COMPOSE c ON e.COMPOSE_ID = c.COMPOSE_ID "
		        + "JOIN DEPARTMENTS d ON c.DEPARTMENT_CODE = d.DEPARTMENT_CODE "
		        + "JOIN DEVISIONS v ON c.DEVISION_CODE = v.DEVISION_CODE "
		        + "WHERE 1=1 "; // 1=1は後で条件を追加するためのプレースホルダー

		List<String> conditions = new ArrayList<>(); // 条件を格納するリスト
		
		conditions.add("a.YEAR = '" + data.getDate() + "'");
		
		if (!data.getEmpName().isEmpty()) {
		    conditions.add("e.NAME_JP = '" + data.getEmpName() + "'"); // 社員名でフィルタリング
		}

		if (!data.getEmpNum().isEmpty()) {
		    conditions.add("a.EMPLOYEE_NUM = '" + data.getEmpNum() + "'");
		}

		if (!data.getComposeId().equals("0")) { // equalsを使用して文字列比較
		    conditions.add("c.COMPOSE_ID = '" + data.getComposeId() + "'"); // COMPOSE_IDを使用
		}

		if (!conditions.isEmpty()) {
		    sql += " AND " + String.join(" AND ", conditions);
		}

		sql += " ORDER BY a.YEAR, e.NAME_JP";
		
		System.out.println("SQL : " + sql);
		
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		
		List<Result> all = new ArrayList<Result>(); // 結果の初期化
		
		for(Map<String, Object> tmp : results) {
			Result result = new Result();
			result.setDate((Date)tmp.get("日付"));
			result.setEmpName((String)tmp.get("社員名"));
			result.setComposeId((String)tmp.get("部署"));
			result.setAttendanceType((String)tmp.get("出勤区分"));
			result.setStartTime((String)tmp.get("出勤時間"));
			result.setFinishTime((String)tmp.get("退勤時間"));
			result.setRestTime((int)tmp.get("休憩分"));
			all.add(result);
		}
		
		return all;
	 }
}
