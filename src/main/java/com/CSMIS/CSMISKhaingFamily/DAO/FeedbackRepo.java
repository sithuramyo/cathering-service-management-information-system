package com.CSMIS.CSMISKhaingFamily.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CSMIS.CSMISKhaingFamily.Entity.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long>{

//	@Query(value = "SELECT fb FROM Feedback fb WHERE fb.restaurant.status = 'active'")
//	List<Feedback> findAllOrderByDesc();
	
	 
//	List<Feedback> findByOperatorId(Long id);	
	
	 @Modifying
     @Query("DELETE FROM Feedback f WHERE f.createdDate < :date")
     void deleteByCreatedDateBefore(@Param("date") LocalDate date);

//	List<Feedback> findByCreatedDateBetween(LocalDate startOfPreviousMonth, LocalDate endOfPreviousMonth);
	 
	    List<Feedback> findByCreatedDateBetween(LocalDate start, LocalDate end);

	    List<Feedback> findAll();
	    
	    List<Feedback> findByCreatedDateBefore(LocalDate date);

	    List<Feedback> findByCreatedDateAfter(LocalDate date);


}