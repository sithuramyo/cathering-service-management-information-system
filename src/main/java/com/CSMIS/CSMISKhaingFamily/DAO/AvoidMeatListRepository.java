package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.CSMIS.CSMISKhaingFamily.Entity.AvoidMeatList;


	public interface AvoidMeatListRepository extends JpaRepository<AvoidMeatList, Long> {
		 
		 @Modifying
		    @Query(value = "DELETE FROM avoidmeatlist WHERE doorlognomeats NOT IN (SELECT doorlognodates FROM registerdate)", nativeQuery = true)
		    void deleteAvoidMeatsWithNoRegisterDate();
		}

