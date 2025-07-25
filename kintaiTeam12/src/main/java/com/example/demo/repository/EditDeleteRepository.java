package com.example.demo.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Summary;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EditDeleteRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Summary> findByAttends(String empnum) {
    	
    	LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
    	
        String sql1 = "SELECT "
        		+ "    A.ATTENDANCE_ID,"
        		+ "    A.EMPLOYEE_NUM,"
        		+ "    E.NAME_JP AS EMPLOYEE_NAME,"
        		+ "    A.YEAR,"
        		+ "    A.ATTENDANCE_TYPE,"
        		+ "    A.START_HOUR,"
        		+ "    A.START_MINUTE,"
        		+ "    A.FINISH_HOUR,"
        		+ "    A.FINISH_MINUTE,"
        		+ "    A.REST_TIME "
        		+ "FROM "
        		+ "    ATTENDANCES A "
        		+ "JOIN "
        		+ "    EMPLOYEES E ON A.EMPLOYEE_NUM = E.EMPLOYEE_NUM "
        		+ "WHERE "
        		+ "    E.EMPLOYEE_NUM = ? AND "
        		+ "    A.YEAR BETWEEN ? AND ?;" ;
    	
        List<Map<String, Object>> monthAttendances = jdbcTemplate.queryForList(sql1, empnum, firstDayOfMonth, lastDayOfMonth);
    	
        List<Summary> all = new ArrayList<Summary>();
        
        for(Map<String, Object>monthAttendance:monthAttendances ) {
        	Summary result = new Summary();
        	result.setAttendanceId((int)monthAttendance.get("ATTENDANCE_ID"));
        	result.setEmployeeNum((String)monthAttendance.get("EMPLOYEE_NUM"));
        	result.setEmpName((String)monthAttendance.get("EMPLOYEE_NAME"));
        	result.setDate((Date)monthAttendance.get("YEAR"));
        	result.setAttendanceType((String)monthAttendance.get("ATTENDANCE_TYPE"));
        	result.setStartHour((int)monthAttendance.get("START_HOUR"));
        	result.setStartMinute((int)monthAttendance.get("START_MINUTE"));
        	result.setFinishHour((int)monthAttendance.get("FINISH_HOUR"));
        	result.setFinishMinute((int)monthAttendance.get("FINISH_MINUTE"));
        	result.setRestTime((int)monthAttendance.get("REST_TIME"));
			all.add(result);
        }

        return all;
    }
}
