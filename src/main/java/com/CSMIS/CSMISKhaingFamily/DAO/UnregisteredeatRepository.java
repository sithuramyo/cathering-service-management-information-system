package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.Entity.Registeredeat;
import com.CSMIS.CSMISKhaingFamily.Entity.Unregisteredeat;
@Service
public interface UnregisteredeatRepository extends JpaRepository<Unregisteredeat, String> {
	@Query("SELECT ure.id.date FROM Unregisteredeat ure WHERE ure.id.doorlogno = :doorlogno")
	List<String> getURDateForEat(@Param("doorlogno") String doorlogno);


	@Query(value = "SELECT COUNT(*) FROM Unregisteredeat WHERE date = ?1 AND doorlogno = ?2")
	int calPriceWithDate(Date date,String doorLog);
	
	@Query("SELECT r FROM Unregisteredeat r WHERE r.id.date = :date")
	List<Unregisteredeat> getUReByDate(@Param("date") String date);
	
	@Query("SELECT  COALESCE(SUM(o.employeecost), 0) FROM Unregisteredeat AS r JOIN OrderDetailView AS o ON r.id.date = o.dates WHERE MONTH(r.id.date) = MONTH(CURDATE()) AND r.id.doorlogno = :doorlogno")
	int unregisteredeatprice(@Param("doorlogno") String doorLogNos);
	
	@Query("SELECT COALESCE(SUM(o.employeecost), 0) FROM Unregisteredeat AS r JOIN OrderDetailView AS o ON r.id.date = o.dates WHERE MONTHNAME(r.id.date) = :month AND r.id.doorlogno = :doorlogno")
	int unregisteredeatpricefor(@Param("doorlogno") String doorLogNos, @Param("month") String month);
	
	@Query("SELECT count(*) FROM Unregisteredeat AS r WHERE MONTHNAME(r.id.date) = :month AND r.id.doorlogno = :doorlogno")
	int countue(@Param("doorlogno") String doorLogNos,@Param("month") String month);
}
