package com.CSMIS.CSMISKhaingFamily.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

//
@Entity
@Table(name="feedback")
public class Feedback implements Serializable{

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", operator=" + operator + ", ratings=" + ratings
				+ ", quality=" + quality + ", cost=" + cost + ", cleanese=" + cleanese + ", suggest=" + suggest
				+ "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "staff_id" ,referencedColumnName = "staffId")
	private Operator operator;
	
	@OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL)
	private List<Rating> ratings;
	
	@Column(name = "created_date")
	private LocalDate createdDate;
	
	private int quality;

	private int cost;
	
	private int cleanese;

	private String suggest;
  
	public Feedback() {
        ratings = new ArrayList<>();

	}
	
	 
    public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
	public void addRating(String category, int rating) {
        Rating newRating = new Rating();
        newRating.setCategory(category);
        newRating.setRating(rating);
        newRating.setFeedback(this);
        ratings.add(newRating);
        
        switch (category) {
        case "quality":
            setQuality(rating);
            break;
        case "cost":
            setCost(rating);
            break;
        case "cleanese":
            setCleanese(rating);
            break;
        // Add more cases for other categories if needed
    }
  }
	
	public boolean hasRating(String category, int rating) {
        for (Rating r : ratings) {
            if (r.getCategory().equals(category) && r.getRating() == rating) {
                return true;
            }
        }
        return false;
    }
	
	public int getRating(String category) {
	    for (Rating r : ratings) {
	        if (r.getCategory().equals(category)) {
	            return r.getRating();
	        }
	    }
	    return 0; // Return a default value if the rating doesn't exist
	}

	public int getQuality() {
	    return quality;
	}

	public void setQuality(int quality) {
	    this.quality = quality;
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCleanese() {
		return cleanese;
	}

	public void setCleanese(int cleanese) {
		this.cleanese = cleanese;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
    
}