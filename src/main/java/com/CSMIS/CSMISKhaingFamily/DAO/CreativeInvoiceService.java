package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreativeInvoiceService {

	@Autowired 
	CreativeInvoiceRepository invoiceRepository;
	
	@Autowired 
	AvoidMeatListRepository avoidRepo;
	
	
	public String generateUserId() {
        int count = invoiceRepository.countInvoices() + 1;
        String invoiceId = null;
        
        if (count < 10) {
            invoiceId = "CS001-2023020" + count;
        } else if (count >= 10 && count < 100) {
            invoiceId = "CS001-202302" + count;
        } else if (count == 100) {
            invoiceId = "CS001-20230" + count;
        }
        
        return invoiceId;
    }
	public int deleteOrder(String id) 
    { 
    	int result = 0;
    	invoiceRepository.deleteById(id);
    	result = 1;
    	return result;
    }
}