package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoRepository;

@Service
public class RestaurantService {

	@Autowired 
	RestaurantInfoRepository resRepository;
	
	public String generateUserId() {
        int count = resRepository.countRes() + 1;
        String invoiceId = null;
        
        if (count < 10) {
            invoiceId = "R-00" + count;
        } else if (count >= 10 && count < 100) {
            invoiceId = "R-0" + count;
        } else if (count == 100) {
            invoiceId = "R-" + count;
        }
        else if (count > 100) {
            invoiceId = "R-" + count;
        }
        return invoiceId;
    }
	
}
