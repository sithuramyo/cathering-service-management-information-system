package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToogleService {

	@Autowired
	ToogleRepository repo;
	
	public int updateToogleWithEmail(String toogle,String email) 
	{ 
		int i = 0;
		i = repo.updateWithEmail(toogle, email);
		
		return i;
	}
}
