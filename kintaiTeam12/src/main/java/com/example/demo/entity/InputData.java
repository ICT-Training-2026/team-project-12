package com.example.demo.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class InputData {
	
	private String employeeNum;
	
	private Date date;
	private String empName;
	private String empNum;
	private String composeId;
	private String composeName;
}
