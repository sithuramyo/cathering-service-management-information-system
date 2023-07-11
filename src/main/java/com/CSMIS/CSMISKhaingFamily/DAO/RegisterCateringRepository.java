package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CSMIS.CSMISKhaingFamily.Entity.RegisterCatering;

public interface RegisterCateringRepository extends JpaRepository<RegisterCatering, String> {

	
	@Query("SELECT rd.dates FROM RegisterCatering rc JOIN rc.dateList rd WHERE rc.doorLogNo = :doorlogno")
	List<String> getDate(@Param("doorlogno") String doorlogno);
	
	@Query("SELECT aml.avoid FROM RegisterCatering rc JOIN rc.meatList aml WHERE rc.doorLogNo = :doorlogno")
	List<String> getMeat(@Param("doorlogno") String doorlogno);
	
	@Modifying
    @Query(value = "DELETE FROM registercatering WHERE door_log_no NOT IN (SELECT doorlognodates FROM registerdate)", nativeQuery = true)
    void deleteRegisterCateringWithNoRegisterDate();
	
}
