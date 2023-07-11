package com.CSMIS.CSMISKhaingFamily.DAO;



import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CSMIS.CSMISKhaingFamily.Entity.Feedback;
import com.CSMIS.CSMISKhaingFamily.Entity.Rating;

@Service
public class FeedbackService{

	@Autowired
	private FeedbackRepo fRepo;
	
	public void createFeedback(Feedback feedback) {
	    feedback.setCreatedDate(LocalDate.now()); // Set the current date
		fRepo.save(feedback);
	}
	
	public List<Rating> getRatingsByFeedbackId(Long feedbackId) {
        Feedback feedback = fRepo.findById(feedbackId).orElse(null);
        if (feedback != null) {
            return feedback.getRatings();
        }
        return Collections.emptyList();
    }
	
	public Feedback saveFeedback(Feedback feedback) {
        return fRepo.save(feedback);
    }
	
	  public List<Feedback> getAllFeedbacks() {
	        return fRepo.findAll();
	    }
}