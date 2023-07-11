package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.Entity.Registeredeat;
import com.CSMIS.CSMISKhaingFamily.Entity.Registereduneat;
@Service
public interface RegistereduneatRepository extends JpaRepository<Registereduneat, String> {
	@Query("SELECT rue.id.dates FROM Registereduneat rue WHERE rue.id.doorLogNo = :doorlogno")
	List<String> getRDateForNoEat(@Param("doorlogno") String doorlogno);
	
	@Query(value = "SELECT COUNT(*) FROM Registereduneat WHERE dates = ?1 AND door_log_no = ?2")
	int calPriceWithDate(String dates,String doorLog);
	
	@Query("SELECT r FROM Registereduneat r WHERE r.id.dates = :date")
	List<Registereduneat> getRNeByDate(@Param("date") String date);
	
	@Query("SELECT  COALESCE(SUM(o.employeecost), 0) FROM Registereduneat AS r JOIN OrderDetailView AS o ON r.id.dates = o.dates WHERE MONTH(r.id.dates) = MONTH(CURDATE()) AND r.id.doorLogNo = :doorlogno")
	int registereduneatprice(@Param("doorlogno") String doorLogNos);
	
	@Query("SELECT COALESCE(SUM(o.employeecost), 0) FROM Registereduneat AS r JOIN OrderDetailView AS o ON r.id.dates = o.dates WHERE MONTHNAME(r.id.dates) = :month AND r.id.doorLogNo = :doorlogno")
	int registereduneatpricefor(@Param("doorlogno") String doorLogNos, @Param("month") String month);
	
	@Query("SELECT count(*) FROM Registereduneat AS r WHERE MONTHNAME(r.id.dates) = :month AND r.id.doorLogNo = :doorlogno")
	int countrne(@Param("doorlogno") String doorLogNos,@Param("month") String month);
}
