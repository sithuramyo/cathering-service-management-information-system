package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.CSMIS.CSMISKhaingFamily.Entity.AvoidCount;

public interface AvoidCountRepository extends JpaRepository<AvoidCount, Long> {
	
	@Query(value = "SELECT count(*) FROM AvoidCount where dates = ?1 and avoid = ?2")
	int countAvoid(String date,String avoid); 
}
