package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CSMIS.CSMISKhaingFamily.Entity.AvoidMeat;

public interface AvoidMeatRepository extends JpaRepository<AvoidMeat, Long> {
	
	@Query("SELECT a FROM AvoidMeat a WHERE LOWER(a.meatName) LIKE %:meatName%")
	 List<AvoidMeat> findmeat(@Param("meatName") String meatName);
	
	@Transactional
	@Modifying
	@Query("UPDATE AvoidMeat Set meatName = :name Where id = :id")
    void updateAvoidMeatNameById(@Param("id") Long id, @Param("name") String name);

    AvoidMeat findByMeatName(String meatName);
	    
    @Transactional
	@Modifying
	@Query("Delete From AvoidMeat Where id = :id")
    void deleteById(@Param("id") Long id);
}
