package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.Entity.Registeredeat;
@Service
public interface RegisteredeatRepository extends JpaRepository<Registeredeat, String> {
	
	@Query("SELECT re.id.dates FROM Registeredeat re WHERE re.id.doorLogNo = :doorlogno")
	List<String> getRDateForEat(@Param("doorlogno") String doorlogno);
	
	@Query("SELECT r FROM Registeredeat r WHERE r.id.dates = :date")
	List<Registeredeat> getReByDate(@Param("date") String date);
	
	@Query(value = "SELECT COUNT(*) FROM Registeredeat WHERE dates = ?1 AND door_log_no = ?2")
	int calPriceWithDate(String dates,String doorLog);
	
	@Query("SELECT COALESCE(SUM(o.employeecost), 0) FROM Registeredeat AS r JOIN OrderDetailView AS o ON r.id.dates = o.dates WHERE MONTH(r.id.dates) = MONTH(CURDATE()) AND r.id.doorLogNo = :doorlogno")
	int registeredeatprice(@Param("doorlogno") String doorLogNos);
	
	@Query("SELECT COALESCE(SUM(o.employeecost), 0) FROM Registeredeat AS r JOIN OrderDetailView AS o ON r.id.dates = o.dates WHERE MONTHNAME(r.id.dates) = :month AND r.id.doorLogNo = :doorlogno")
	int registeredeatpricefor(@Param("doorlogno") String doorLogNos, @Param("month") String month);
	
	@Query("SELECT count(*) FROM Registeredeat AS r WHERE MONTHNAME(r.id.dates) = :month AND r.id.doorLogNo = :doorlogno")
	int countre(@Param("doorlogno") String doorLogNos,@Param("month") String month);

}
