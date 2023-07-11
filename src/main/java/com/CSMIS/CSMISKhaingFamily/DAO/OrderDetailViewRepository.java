package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CSMIS.CSMISKhaingFamily.Entity.LunchCount;
import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetailView;

public interface OrderDetailViewRepository extends JpaRepository<OrderDetailView, Long> {
	
	 @Query(value = "SELECT * FROM orderdetailview WHERE dates = ?1 ORDER BY id DESC LIMIT 1", nativeQuery = true)
	 OrderDetailView findLastByDates(String dates);
	 
	 @Query(value = "SELECT id, dates, extrapax, quantity, weekly_order_id, employeecost, datcost, totalpax, doorlogid, orderdate,status,starttoenddate " +
		        "FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY weekly_order_id ORDER BY id) AS row_num " +
		        "FROM orderdetailview) AS subquery " +
		        "WHERE row_num = 1 AND status = 'ACTIVE'", nativeQuery = true)
	 List<OrderDetailView> getAllDistinctWeeklyOrderIds();
	 
	 @Query("SELECT o FROM OrderDetailView o WHERE o.weekly_order_id = :weeklyOrderId AND o.status='ACTIVE'")
	 List<OrderDetailView> findByWeeklyOrderId(Long weeklyOrderId);
	 
	 @Query("SELECT o from OrderDetailView o Where o.status='ACTIVE'")
	 List<OrderDetailView> findAll();
	
			 @Query(value = " SELECT * FROM orderdetailview ORDER BY dates DESC LIMIT 3", nativeQuery = true)
		List<OrderDetailView> getLastFiveRowsLunchCost();
	
}
