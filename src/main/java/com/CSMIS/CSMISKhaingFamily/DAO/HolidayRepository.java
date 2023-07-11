package com.CSMIS.CSMISKhaingFamily.DAO;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CSMIS.CSMISKhaingFamily.Entity.HolidayData;




public interface HolidayRepository extends JpaRepository<HolidayData, String> {
	@Query("SELECT h FROM HolidayData h WHERE h.holidayDate = :date")
	List<HolidayData> finddate(@Param("date") String date);
	
	 @Query("SELECT h FROM HolidayData h WHERE h.holidayDate = :date")
	    HolidayData findByHolidayDate(@Param("date") String date);

	 @Query(value="SELECT holiday_date FROM holidaydata;",nativeQuery = true)
		List<String> GetAllHolidayDate();
}
