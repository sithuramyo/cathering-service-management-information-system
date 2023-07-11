package com.CSMIS.CSMISKhaingFamily.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.DAO.HolidayRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.HolidayService;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisterDateRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.HolidayData;
import java.util.List;

@Service
public class ValitedateHoliday {
	 @Autowired
	    private HolidayRepository holidayRepository;
	 
	 @Autowired
		private RegisterDateRepository registerDateReposiotry;
	 
	 @Autowired
		private HolidayService holidayService;
	 
	 public ValidationResult validateHoliday(String date) {
		    ValidationResult validationResult = new ValidationResult();
		    List<HolidayData> holidayDataList = holidayRepository.finddate(date);
		    

		    for (HolidayData holidayData : holidayDataList) {
		    	System.out.println("Retrieved holiday data: " + holidayData.getHolidayDate());
		        if (holidayData.getHolidayDate().equals(date)) {
		            validationResult.addError("Duplicate Holiday Date found: " + holidayData.getHolidayDate());
		            break; 
		        }
		    }
		    return validationResult;
		}

	 public String addHoiday(String holidayDate , String holidayName) {
		 HolidayData holi = new HolidayData();
			holi.setHolidayDate(holidayDate);
			holi.setHolidayName(holidayName);
			holidayService.saveData(holi);	
			registerDateReposiotry.deleteByDate(holidayDate);
			return holidayDate;
	 }
}
