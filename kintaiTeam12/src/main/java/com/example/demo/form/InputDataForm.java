package com.example.demo.form;

import java.sql.Date;

import lombok.Data;

@Data
public class InputDataForm {
	
	private String employeeNum;
	
	private Date date;
	private String empName;
	private String empNum;
	private String composeId;
}
