package com.CSMIS.CSMISKhaingFamily.DAO;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.Entity.HolidayData;


@Service
public class HolidayService {
	@Autowired
	private HolidayRepository holidayRepository;
		public void saveData(HolidayData data) {
			holidayRepository.save(data);
		}
		public List<HolidayData> getAllHoliday(){
			return holidayRepository.findAll();
		}
		
		public void updateHoliday(String date, String name) {

			// Find the existing holiday by date or any other unique identifier
		    HolidayData holiday = holidayRepository.findByHolidayDate(date);
		    
		    // Update the name of the holiday
		    holiday.setHolidayName(name);
		    
		    // Save the updated holiday in the database
		    holidayRepository.save(holiday);
		}
}
