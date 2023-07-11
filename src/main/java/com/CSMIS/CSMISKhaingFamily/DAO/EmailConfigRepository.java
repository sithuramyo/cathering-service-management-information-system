package com.CSMIS.CSMISKhaingFamily.DAO;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CSMIS.CSMISKhaingFamily.Entity.EmailConfig;




@Repository
public interface EmailConfigRepository extends JpaRepository<EmailConfig, String> 
{
	 @Modifying
	 @Transactional
	 @Query("UPDATE EmailConfig u SET u.cronExpression =?1  WHERE u.id = 1")
	 int updateCronExpression(String cronExpression);
	 
	 
}