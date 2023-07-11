package com.CSMIS.CSMISKhaingFamily.Controller;


import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.FeedbackRepo;
import com.CSMIS.CSMISKhaingFamily.DAO.FeedbackService;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.CreativeInvoice;
import com.CSMIS.CSMISKhaingFamily.Entity.Feedback;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Entity.Rating;
import com.CSMIS.CSMISKhaingFamily.Entity.RestaurantInfo;

@Controller
public class FeedbackController {
	
	@Autowired
	OperatorRepository oRepo;
	
	@Autowired
	CreativeInvoiceRepository iRepo;
	
	@Autowired
	FeedbackService fService;
	
	@Autowired
	FeedbackRepo fRepo;

	private Operator operator;

	@GetMapping("/operator/feedback")
	public String feedback(Model model, RedirectAttributes ra, HttpSession session) {
		Feedback feedback = new Feedback(); // Create a new Feedback object
	    String suggest = (String) ra.getFlashAttributes().get("suggest");
	    List<Feedback> latestFeedbackList = fRepo.findAll();
	    
	    System.out.println(latestFeedbackList + "hi");
	    System.out.println("Latest Feedback List: " + latestFeedbackList);

	    
	    if (!latestFeedbackList.isEmpty()) {
	    	 Feedback latestFeedback = latestFeedbackList.get(0);
	    	 model.addAttribute("suggestions", latestFeedback.getSuggest());
	    	 model.addAttribute("restaurant", latestFeedback);
	    } else {
	        model.addAttribute("suggestions", ""); // Provide a default value when latestFeedback is null
	    }
	    if (suggest != null) {
	        feedback.setSuggest(suggest);
	    }
	    

	 // Retrieve the operator and restaurant details from session attributes
	    Operator operator = (Operator) session.getAttribute("operator");


		model.addAttribute("feedback", feedback); // Add it as a model attribute
		if(operator == null) {
			model.addAttribute("operatorName", "");
	        return "operatorfeedback";

		}else {
			 model.addAttribute("operatorName", operator.getEmployeeName());
		     return "operatorfeedback";
	
		}
	}

	
	@PostMapping("/operator/review")
	public String review(Model model, @ModelAttribute("feedback") @Valid Feedback feedback, BindingResult bindingResult, 
		@RequestParam("qualityRating") int qualityRating, @RequestParam("costRating") int costRating,
		@RequestParam("cleaneseRating") int cleaneseRating,
		
		HttpSession session, RedirectAttributes ra) {
		 if (bindingResult.hasErrors()) {
			 model.addAttribute("feedback", feedback);
            return "operatorfeedback";
	    }
		  Operator operator = (Operator) session.getAttribute("operator");
//		    RestaurantInfo restaurant = (RestaurantInfo) session.getAttribute("restaurant");

		    // Set the operator and restaurant in the feedback object
		    feedback.setOperator(operator);

		    // Add the ratings to the feedback object
		    feedback.addRating("quality", qualityRating);
		    feedback.addRating("cleanese", cleaneseRating);
		    feedback.addRating("cost", costRating);
		   
		    fService.createFeedback(feedback);
		    
		    // Get the feedback ID
		    Long feedbackId = feedback.getId();
		 
		    // Retrieve the ratings for the feedback ID
		    List<Rating> ratings = fService.getRatingsByFeedbackId(feedbackId);
		    session.setAttribute("feedbackId", feedbackId);
		    session.setAttribute("ratings", ratings);
		    
		    System.out.println(feedbackId + " wer " + ratings);    
		    
		    // Save the updated feedback object
		    fService.saveFeedback(feedback); 
		    
		    // Add the rating values as model attributes
		    int updatedQualityRating = feedback.getRating("quality");
		    int updatedCostRating = feedback.getRating("cost");
		    int updatedCleaneseRating = feedback.getRating("cleanese");
		    
		    session.setAttribute("qualityRating", updatedQualityRating);
		    session.setAttribute("costRating", updatedCostRating);
		    session.setAttribute("cleaneseRating", updatedCleaneseRating);
		    model.addAttribute("successMessage", "Successfully Suggested!!");
		 
		    return "operatorfeedback";
	}
}