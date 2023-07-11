package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.CSMIS.CSMISKhaingFamily.Entity.CreativeInvoice;

public interface CreativeInvoiceRepository extends JpaRepository<CreativeInvoice, String> 
{
	
	 @Query(value = "SELECT COUNT(*) FROM CreativeInvoice")
	 int countInvoices();
	 
	 @Query(value = "SELECT count(*) FROM RegisterDate where dates = ?1")
	 int countDates(String date);
	
	 @Query(value = "SELECT count(*) FROM RegisterDate where dates = ?1 and door_log_no_dates=?2")
	 int countRegisterDates(String date,String doorlog);
	 
	 @Query("SELECT s from CreativeInvoice s where s.id=?1")
	 CreativeInvoice getInvoiceInfo(String id);
	 
	 @Modifying
	  @Transactional
	  @Query(value = "DELETE From CreativeInvoice s WHERE s.id=?1",nativeQuery = true)
	  void deleteById(String id);
}