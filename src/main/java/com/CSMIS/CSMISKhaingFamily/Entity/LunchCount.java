package com.CSMIS.CSMISKhaingFamily.Entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lunchcount")
public class LunchCount {

    @Id
    public String date;

  public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

public Long getRe() {
    return re;
  }

  public void setRe(Long re) {
    this.re = re;
  }

  public Long getRne() {
    return rne;
  }

  public void setRne(Long rne) {
    this.rne = rne;
  }

  public Long getUe() {
    return ue;
  }

  public void setUe(Long ue) {
    this.ue = ue;
  }


    private Long re;


    private Long rne;


    private Long ue;

    // constructors, getters, and setters
}