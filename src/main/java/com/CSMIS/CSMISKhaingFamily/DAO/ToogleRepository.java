package com.CSMIS.CSMISKhaingFamily.DAO;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CSMIS.CSMISKhaingFamily.Entity.Operator;


@Repository
public interface ToogleRepository extends JpaRepository<Operator, String>{

	 @Modifying
	 @Transactional
	 @Query("UPDATE Operator u SET u.toggle=?1 WHERE u.employeeEmail = ?2")
	 int updateWithEmail(String toggle,String email);
	 
	
}
