package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeeklyOrderService {

	@Autowired
	WeeklyOrderRepository wRepo;
	
	public int deleteAllOrder() 
    { 
    	int result = 0;
    	wRepo.deleteAllWeeklyOrders();
    	result = 1;
    	return result;
    }
	public int insertStatusWithId(String status,Long id) 
	{ 
		int i = 0;
		wRepo.updateStatusWithId(status, id);
		i = 1;
		return i;
	}
	public int updateStatus() 
	{ 
		int i = 0;
		wRepo.updateStatusToInactive();
		i = 1;
		return i;
	}
}