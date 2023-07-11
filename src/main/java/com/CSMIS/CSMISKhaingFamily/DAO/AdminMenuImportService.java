package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.Entity.MenuImportData;

@Service
public class AdminMenuImportService {
	@Autowired
	private AdminMenuImportRepo repo;

	public MenuImportData createCatering(MenuImportData catering) {
		return repo.save(catering);
	}
	
	public List<MenuImportData> getAllCatering(){
		return repo.findAll();
	}
	
	
}
