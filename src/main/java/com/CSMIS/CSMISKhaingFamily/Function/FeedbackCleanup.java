package com.CSMIS.CSMISKhaingFamily.Function;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.CSMIS.CSMISKhaingFamily.DAO.FeedbackRepo;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Feedback;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;

public class FeedbackCleanup implements Job{

	@Autowired
    private FeedbackRepo fRepo;
	
	@Autowired
	private OperatorRepository oRepo;
	
	 @Override
	    public void execute(JobExecutionContext context) throws JobExecutionException {
	        // Get the current date
	        LocalDate currentDate = LocalDate.now();
	        
	        // Get the last day of the current month
	        LocalDate lastDayOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
	        
	        // Delete feedbacks created before the last day of the current month
	        fRepo.deleteByCreatedDateBefore(lastDayOfMonth);
	        
//	     // Calculate the start and end dates for the previous month
//	        LocalDate startOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);
//	        LocalDate endOfPreviousMonth = currentDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
//	        
//	        
//	        // Retrieve the feedbacks created within the previous month
//	        List<Feedback> feedbackList = fRepo.findByCreatedDateBetween(startOfPreviousMonth, endOfPreviousMonth);
//	        
//	        // Fetch the complete Operator object for each feedback
//	        for (Feedback feedback : feedbackList) {
//	            Operator operator = feedback.getOperator();
//	            if (operator != null) {
//	                Operator completeOperator = oRepo.findById(operator.getId()).orElse(null);
//	                feedback.setOperator(completeOperator);
//	            }
//	        }
	        
	    }

}
