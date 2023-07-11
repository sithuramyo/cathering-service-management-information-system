package com.CSMIS.CSMISKhaingFamily.Entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class Doorlog {

    @EmbeddedId
    private DoorlogKey id;

    private String department;

	public DoorlogKey getId() {
		return id;
	}

	public void setId(DoorlogKey id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

  
}
