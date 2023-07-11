package com.CSMIS.CSMISKhaingFamily.DAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
@Service
public class OperatorService {
	@Autowired 
	OperatorRepository opRepository;

   
    public int insertUserWithPas(String otpCode,Instant otpCreationTime,String email) 
	{ 
		int i = 0;
		opRepository.updateWithEmail(otpCode,otpCreationTime,email);
		i = 1;
		return i;
	}
    public void updatePassword(Operator customer, String newPassword) {
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(newPassword);
        customer.setEmployeePassword(encodedPassword);
         
        
        opRepository.save(customer);
    }
}
