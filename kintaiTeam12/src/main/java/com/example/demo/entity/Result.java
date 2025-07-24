package com.example.demo.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Result {
	private String employeeNum;
	
	private Date date;
	private String empName;
	private String composeId;
	private String attendanceType;
	private String startTime;
	private String finishTime;
	private int restTime;
}
