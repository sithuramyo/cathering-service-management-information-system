package com.CSMIS.CSMISKhaingFamily.Entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;
    
    private String category;

    private int rating;
    
    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
        updateFeedbackRating();
    }

	private void updateFeedbackRating() {
		if (feedback != null) {
            switch (category) {
                case "quality":
                    feedback.setQuality(rating);
                    break;
                case "cost":
                    feedback.setCost(rating);
                    break;
                case "cleanese":
                    feedback.setCleanese(rating);
                    break;
                // Add more cases for other categories if needed
            }
        }		
	}
    
}