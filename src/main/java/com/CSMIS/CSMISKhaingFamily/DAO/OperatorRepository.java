package com.CSMIS.CSMISKhaingFamily.DAO;

import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
@Service
public interface OperatorRepository extends JpaRepository<Operator, Long> {
	Operator findByemployeeEmail (String email);
	
	Operator findBystaffId(String staffId);
	
	@Query("SELECT c from Operator c where c.dept='HR/Admin Dept'")
	 List<Operator> searchUser();
	 
	 @Query("SELECT c from Operator c where c.role='ADMIN'")
	 List<Operator> searchAdmin();
	 
	 @Query("SELECT p FROM Operator p where p.staffId!='default_admin'")
	 List<Operator> findAllEmployee();
	 
	 @Query("SELECT c from Operator c where c.doorLogNo = ?1")
	 Operator searchEmployee(String doorlogid);
	 
	 
	 @Modifying
	 @Transactional
	 @Query("UPDATE Operator u SET u.otpCode=?1,u.otpCreationTime=?2 WHERE u.employeeEmail = ?3")
	 int updateWithEmail(String otpCode,Instant otpCreationTime,String email);
	 
	 @Modifying
	   @Transactional
	   @Query("UPDATE Operator u SET u.employeeName=?1 WHERE u.employeeEmail = ?2")
	   int updateWithEmail1(String employeeName,String email);
	 
	 @Modifying
	 @Transactional
	 @Query("UPDATE Operator u SET u.employeePassword=?1  WHERE u.employeeEmail = ?2")
	 int updateWithEmail2(String employeePassword,String employeeEmail,String email);
	 
	 @Query(value= "select u from Operator u where u.employeeEmail=?1 ")
		Operator SelectOne(String employeeEmail);
	 
	 @Query("SELECT count(*) FROM Operator  where toggle='true'")
	 int countNotiOperator();
}
