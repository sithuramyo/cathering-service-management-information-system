package com.CSMIS.CSMISKhaingFamily.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
@Service
public class MyOperatorDetailsService implements UserDetailsService {
	@Autowired
	OperatorRepository operatorRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Operator operator = operatorRepository.findByemployeeEmail(username);
	    if(operator == null) {
	        throw new UsernameNotFoundException("404 Your name is not found");
	    }
	    return operator;
	}


}
