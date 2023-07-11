package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CSMIS.CSMISKhaingFamily.Entity.LunchCount;



@Repository
public interface LunchCountRepository extends JpaRepository<LunchCount,Long>{
	@Query(value = "SELECT * FROM lunchcount ORDER BY date DESC LIMIT 7", nativeQuery = true)
	List<LunchCount> getLastFiveRowsLunchCount();

@Query(value="SELECT * FROM lunchcount order by date asc;",nativeQuery=true)
List<LunchCount> findAll();
	
}
