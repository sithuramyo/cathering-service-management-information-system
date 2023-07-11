package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.Entity.Registerfinal1;
@Service
public interface Registerfinal1Repository extends JpaRepository<Registerfinal1, String> {
	@Query("SELECT dates FROM Registerfinal1 WHERE doorLogNo = :doorlogno")
	List<String> getRDate(@Param("doorlogno") String doorlogno);
}
