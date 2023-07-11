package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyOrder;
import com.CSMIS.CSMISKhaingFamily.Model.OrderDetailWithWeeklyOrderDTO;

public interface WeeklyOrderRepository extends JpaRepository<WeeklyOrder, Long> {

	 @Query("SELECT s from WeeklyOrder s where s.starttoenddate=?1")
	 List<WeeklyOrder> dateList(String date);
	 
	 @Query("SELECT s from WeeklyOrder s where s.status='ACTIVE'")
	 List<WeeklyOrder> datesList();
	 
	 @Query("SELECT s from WeeklyOrder s where s.starttoenddate=?1")
	 List<WeeklyOrder> costWithDate(String starttoenddate);
	 
	 @Query("SELECT od, wo.employeecost, wo.datcost, wo.totalpax " +
		        "FROM OrderDetail od JOIN od.weeklyOrder wo WHERE od.dates = ?1")
		List<OrderDetailWithWeeklyOrderDTO> findByDates(String date);
	 
	 @Query("SELECT u\r\n"
	 		+ "FROM WeeklyOrder u \r\n"
	 		+ "WHERE FIND_IN_SET('2023-05-16', weekdays) > 0\r\n"
	 		+ "  AND FIND_IN_SET('2023-05-18', weekdays) > 0")
	 List<WeeklyOrder> date();
	 
	 @Query("SELECT s FROM WeeklyOrder s WHERE s.orderdate = (SELECT MAX(s2.orderdate) FROM WeeklyOrder s2) AND s.status='ACTIVE'")
     WeeklyOrder getNewOrder();
	 
	 @Query("SELECT odv " +
		        "FROM WeeklyOrder odv " +
		        "WHERE odv.orderdate >= :startDate " +
		        "AND odv.orderdate <= :endDate AND status='ACTIVE'" )
		List<WeeklyOrder> findByOrderdateBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);
	 
	 @Modifying
	 @Transactional
	 @Query(value = "DELETE From WeeklyOrder s WHERE s.id=?1",nativeQuery = true)
	 void deleteById(Long id);
	 
	 @Query("SELECT COUNT(o.orderdate) FROM WeeklyOrder o WHERE o.status = 'ACTIVE' AND o.orderdate = :orderdate")
	 int countActiveOrderDatesByDate(@Param("orderdate") String orderdate);
	    // Query to delete OrderDetailView by WeeklyOrder ID
	 	@Modifying
	 	@Transactional
	    @Query("DELETE FROM WeeklyOrder")
	    void deleteAllWeeklyOrders();
	    /////
	 	 @Modifying
		 @Transactional
		 @Query("UPDATE WeeklyOrder SET status=?1 WHERE id = ?2")
		 int updateStatusWithId(String status,Long id);
	 	 
	 	 @Modifying
	     @Transactional
	     @Query("UPDATE WeeklyOrder SET status = 'INACTIVE'")
	     int updateStatusToInactive();
	 	 
	 	WeeklyOrder findTopByOrderByIdDesc();
	 	
	 	 @Query("SELECT s from WeeklyOrder s where s.orderdate=?1 AND s.status='ACTIVE'")
		 WeeklyOrder orderDatesList(String date);
}