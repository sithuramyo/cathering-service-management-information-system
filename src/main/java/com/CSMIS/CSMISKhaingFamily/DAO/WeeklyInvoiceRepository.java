package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyInvoice;




public interface WeeklyInvoiceRepository extends JpaRepository<WeeklyInvoice, Long> {

	 @Query("SELECT s from WeeklyInvoice s where s.id=?1")
	 WeeklyInvoice searchInvoice(Long id);
	 
	 @Query("SELECT u FROM WeeklyInvoice u WHERE u.status = 'Paid'")
	 List<WeeklyInvoice> paidList();
	 
	 @Query("SELECT u FROM WeeklyInvoice u WHERE u.status = 'Unpaid'")
	 List<WeeklyInvoice> unpaidList();
	 
	 @Modifying
	 @Transactional
	 @Query("UPDATE WeeklyInvoice u SET u.status=?1 WHERE u.id = ?2")
	 int updateStatusWithId(String status,Long id);
	 
	 @Query("SELECT u FROM WeeklyInvoice u WHERE u.status = 'Paid'")
	 List<WeeklyInvoice> monthlyInvoiceList();
	 
	 @Query("SELECT u FROM WeeklyInvoice u WHERE u.paymentdate = ?1")
	 List<WeeklyInvoice> monthlyInvoiceSearch(String paymentdate);
	 
	 @Query("SELECT s from WeeklyInvoice s")
	 List<WeeklyInvoice> dateList();
	 
	 @Query("SELECT w FROM WeeklyInvoice w WHERE w.paymentdate >= ?1 AND w.paymentdate <= ?2 AND w.status='Paid'")
	 List<WeeklyInvoice> searchPaidInvoice(String sdate,String edate);
	 
	 @Query("SELECT w FROM WeeklyInvoice w WHERE w.paymentdate >= ?1 AND w.paymentdate <= ?2 AND w.status='Unpaid'")
	 List<WeeklyInvoice> searchUnpaidInvoice(String sdate,String edate);
	 
	  @Query("SELECT wi FROM WeeklyInvoice wi WHERE wi.paymentdate BETWEEN :startDate AND :endDate")
	  List<WeeklyInvoice> findAllByInvoicedateBetween(String startDate, String endDate);
	
	  @Modifying
	  @Transactional
	  @Query(value = "DELETE From WeeklyInvoice s WHERE s.id=?1",nativeQuery = true)
	  void deleteById(Long id);
	  
	  @Query("SELECT u FROM WeeklyInvoice u WHERE u.id = ?1")
	  WeeklyInvoice weeklyInvoiceList(Long id);
	  
		@Modifying
		@Transactional
	    @Query("DELETE FROM WeeklyInvoice w where w.status='Paid'")
	    void deleteAllWeeklyPaid();
		
		@Modifying
	 	@Transactional
	    @Query("DELETE FROM WeeklyInvoice w where w.status='Unpaid'")
	    void deleteAllWeeklyUnpaid();
		
		  @Modifying
		  @Transactional
		  @Query(value = "DELETE From WeeklyInvoice s WHERE s.orderid=?1",nativeQuery = true)
		  void deleteByOrderId(Long id);
		  
		 
}
