package com.example.demo.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ExportRepository {
    private final JdbcTemplate jdbcTemplate;

    public void makeCSV() {
        String sql = "SELECT EMPLOYEE_NUM, NAME_EN FROM employees";
        List<Map<String, Object>> allEmployeeInfo = jdbcTemplate.queryForList(sql);

        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());

        String sql1 = "SELECT EMPLOYEE_NUM, START_HOUR, START_MINUTE, FINISH_HOUR, FINISH_MINUTE, REST_TIME, " +
                "((FINISH_HOUR * 60 + FINISH_MINUTE) - (START_HOUR * 60 + START_MINUTE) - REST_TIME) AS ACTUAL_WORKING_HOURS, " +
                "CASE " +
                "    WHEN ((FINISH_HOUR * 60 + FINISH_MINUTE) - (START_HOUR * 60 + START_MINUTE) - REST_TIME) > 420 " +
                "    THEN ((FINISH_HOUR * 60 + FINISH_MINUTE) - (START_HOUR * 60 + START_MINUTE) - REST_TIME - 420) " +
                "    ELSE 0 " +
                "END AS OVER_TIME " +
                "FROM attendances " +
                "WHERE EMPLOYEE_NUM = ? " +
                //"AND ATTENDANCE_TYPE IN ('出勤', '年休', '振出') " +
                "AND YEAR BETWEEN ? AND ?";
        
        System.out.println("###################################################");
        
        for (Map<String, Object> employee : allEmployeeInfo) {
            Object employeeNum = employee.get("EMPLOYEE_NUM");
            List<Map<String, Object>> userAttendances = jdbcTemplate.queryForList(sql1, employeeNum, firstDayOfMonth, lastDayOfMonth);
            
            //社員番号_名前_年月
            String savepath = "./kintaicsv/";
            String fname = employee.get("EMPLOYEE_NUM") + "_" + employee.get("NAME_EN") + "_"+ currentDate.getYear() + "-"+currentDate.getMonthValue() + ".csv";
            
            //System.out.println("社員コード 年月 始業時刻（時） 始業時刻（分） 終業時刻（時） 終業時刻（分） 労働時間（分） 休憩時間（分）超過時間（分）");
            try (FileWriter writer = new FileWriter(savepath + fname)) {
            	writer.append("社員コード,年月,始業時刻（時）,始業時刻（分）,終業時刻（時）,終業時刻（分）,労働時間（分）,休憩時間（分）,超過時間（分）\n");
            	for (Map<String, Object> userAttendance : userAttendances) {
                    writer.append(userAttendance.get("EMPLOYEE_NUM").toString()).append(",")
                          .append(currentDate.getYear() + "-" + currentDate.getMonthValue()).append(",")
                          .append(userAttendance.get("START_HOUR").toString()).append(",")
                          .append(userAttendance.get("START_MINUTE").toString()).append(",")
                          .append(userAttendance.get("FINISH_HOUR").toString()).append(",")
                          .append(userAttendance.get("FINISH_MINUTE").toString()).append(",")
                          .append(userAttendance.get("ACTUAL_WORKING_HOURS").toString()).append(",")
                          .append(userAttendance.get("REST_TIME").toString()).append(",")
                          .append(userAttendance.get("OVER_TIME").toString()).append("\n");
                }
            	
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}