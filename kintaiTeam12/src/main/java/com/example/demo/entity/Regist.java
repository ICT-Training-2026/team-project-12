package com.example.demo.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Regist {

	private String employeeNum;
	
	private Date year;
	private String attendanceType;
	private int startHour;
	private int startMinute;
	private int finishHour;
	private int finishMinute;
	private int restTime;
}
