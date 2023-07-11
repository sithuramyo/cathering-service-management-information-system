package com.CSMIS.CSMISKhaingFamily.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holidaydata")
public class HolidayData {
    @Id
    @Column(nullable = false)
    private String holidayDate;

    @Column(nullable = false)
    private String holidayName;

	@Override
	public String toString() {
		return "HolidayData [holidayDate=" + holidayDate + ", holidayName=" + holidayName + "]";
	}
	public HolidayData( String holidayDate, String holidayName) {
		this.holidayDate = holidayDate;
		this.holidayName = holidayName;
	}
	    public HolidayData() {
	        // no-argument constructor
	    
	}


	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

    
}
