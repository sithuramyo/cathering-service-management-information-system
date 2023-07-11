package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.Entity.RegisterDate;
@Service
public interface RegisterDateRepository extends JpaRepository<RegisterDate, Long> {

	@Query(value="SELECT dates\n"
			+ "FROM registerdate\n"
			+ "WHERE MONTH(dates) = MONTH(CURRENT_DATE)\n"
			+ "  AND YEAR(dates) = YEAR(CURRENT_DATE) and doorlognodates=?1",nativeQuery = true)
	List<String> datesinCurrentMonths(String doorlogno);
	
	 @Transactional
	    @Modifying
	    @Query(value = "DELETE FROM registerdate WHERE dates = ?1", nativeQuery = true)
	    void deleteByDate(String date);
}
