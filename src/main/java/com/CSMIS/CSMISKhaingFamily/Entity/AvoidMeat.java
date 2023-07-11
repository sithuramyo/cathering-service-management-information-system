package com.CSMIS.CSMISKhaingFamily.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "avoidmeat")
public class AvoidMeat {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String meatName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMeatName() {
		return meatName;
	}

	public void setMeatName(String meatName) {
		this.meatName = meatName;
	}
}
