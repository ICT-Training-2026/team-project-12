package com.example.demo.service;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.example.demo.repository.HolidayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HolidayService {
	
	private final HolidayRepository hrepository;
	
	public boolean holidayBridge(Date holiday) {

		
		boolean flag = hrepository.findByHoliday(holiday);
		
		
		return flag;
	}
	
	
	public int paidHolidayBridge(String empNum) {
		
		int countPaidHoliday = hrepository.findByPaidHoliday(empNum);
		
		return countPaidHoliday;
	}
	
}
