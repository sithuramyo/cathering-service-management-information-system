package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailService {
	@Autowired 
	OrderDetailRepository orderRepository;
	
	 public int deleteOrder(Long id) 
	    { 
	    	int result = 0;
	    	orderRepository.deleteByWeeklyOrderId(id);
	    	result = 1;
	    	return result;
	    }
	 public int deleteAllOrderDetail() 
	    { 
	    	int result = 0;
	    	orderRepository.deleteAllOrderDetails();
	    	result = 1;
	    	return result;
	    }
}
