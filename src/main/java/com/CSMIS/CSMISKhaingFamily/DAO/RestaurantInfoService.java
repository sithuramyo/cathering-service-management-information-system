package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantInfoService {

	@Autowired
	RestaurantInfoRepository repo;
	
	public int deleteOrder(String id) 
    { 
    	int result = 0;
    	repo.deleteById(id);
    	result = 1;
    	return result;
    }
	public int updateStatus(String id,String name,String address,String phone,String email,String status,String id1) 
	{ 
		int i = 0;
		repo.updateStatusWithId(id,name,address,phone,email,status,id1);
		i = 1;
		return i;
	}
	public int deleteByResId(String id) 
    { 
    	int result = 0;
    	repo.deleteResturant(id);
    	result = 1;
    	return result;
    }
}