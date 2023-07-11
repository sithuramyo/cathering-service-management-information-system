package com.CSMIS.CSMISKhaingFamily.DAO;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	 @Modifying
	 @Transactional
	 @Query(value="DELETE From OrderDetail s WHERE s.weekly_order_id=?1",nativeQuery = true)
	void deleteByWeeklyOrderId(Long weeklyOrderId);
	 	
	 	@Modifying
	 	@Transactional
	    @Query("DELETE FROM OrderDetail")
	    void deleteAllOrderDetails();
}
