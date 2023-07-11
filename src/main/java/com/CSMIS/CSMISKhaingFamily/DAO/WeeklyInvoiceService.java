package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyInvoice;

@Service
public class WeeklyInvoiceService {
	@Autowired
	WeeklyInvoiceRepository wiRepo;
	
	public WeeklyInvoice getInvoiceById(Long id) {
		WeeklyInvoice invoice = wiRepo.searchInvoice(id);
	    	return invoice;
	    	
	}
	public List<WeeklyInvoice> getPaidList() {
		List<WeeklyInvoice> invoice = wiRepo.paidList();
	    	return invoice;
	    	
	}
	public List<WeeklyInvoice> getUnPaidList() {
		List<WeeklyInvoice> invoice = wiRepo.unpaidList();
	    	return invoice;
	    	
	}
	public int insertStatusWithId(String status,Long id) 
	{ 
		int i = 0;
		wiRepo.updateStatusWithId(status, id);
		i = 1;
		return i;
	}
	public List<WeeklyInvoice> getMonthlyInvoiceList() {
		List<WeeklyInvoice> invoice = wiRepo.monthlyInvoiceList();
	    	return invoice;
	    	
	}
	public int deleteOrder(Long id) 
    { 
    	int result = 0;
    	wiRepo.deleteById(id);
    	result = 1;
    	return result;
    }
	public int deleteByOrderId(Long id) 
    { 
    	int result = 0;
    	wiRepo.deleteByOrderId(id);
    	result = 1;
    	return result;
    }
	public int deleteAllPaidInvoice() 
    { 
    	int result = 0;
    	wiRepo.deleteAllWeeklyPaid();
    	result = 1;
    	return result;
    }
	public int deleteAllUnPaidInvoice() 
    { 
    	int result = 0;
    	wiRepo.deleteAllWeeklyUnpaid();
    	result = 1;
    	return result;
    }
}
