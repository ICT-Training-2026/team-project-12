package com.example.demo.form;

import java.sql.Date;

import lombok.Data;

@Data
public class AttendForm {
	
	private Date year;
	private String attendanceType;
	private int startHour;
	private int startMinute;
	private int finishHour;
	private int finishMinute;
	private int restTime;
	
}
