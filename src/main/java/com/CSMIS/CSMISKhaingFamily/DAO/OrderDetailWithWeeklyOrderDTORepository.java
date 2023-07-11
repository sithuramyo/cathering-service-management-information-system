package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyOrder;
import com.CSMIS.CSMISKhaingFamily.Model.OrderDetailWithWeeklyOrderDTO;

public interface OrderDetailWithWeeklyOrderDTORepository extends JpaRepository<WeeklyOrder, Long> {
	
	 @Query("SELECT od, wo.employeecost, wo.datcost, wo.totalpax " +
		        "FROM OrderDetail od JOIN od.weeklyOrder wo WHERE od.dates = ?1")
		List<OrderDetailWithWeeklyOrderDTO> findByDates(String date);
}