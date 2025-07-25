package com.example.demo.form;

import java.sql.Date;

import lombok.Data;

@Data
public class SummaryForm {
	private String employeeNum;
	
	private int attendanceId;
	private Date date;
	private String empName;
	private String attendanceType;
	private int startHour;
	private int startMinute;
	private int finishHour;
	private int finishMinute;
	private int restTime;
}