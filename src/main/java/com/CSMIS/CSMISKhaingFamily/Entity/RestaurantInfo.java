package com.CSMIS.CSMISKhaingFamily.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="restaurantinfo")
public class RestaurantInfo {

	@Id
    private String id;
	private String resturantname;
	private String resph;
	private String resaddress;
	private String resemail;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResturantname() {
		return resturantname;
	}
	public void setResturantname(String resturantname) {
		this.resturantname = resturantname;
	}
	public String getResph() {
		return resph;
	}
	public void setResph(String resph) {
		this.resph = resph;
	}
	public String getResaddress() {
		return resaddress;
	}
	public void setResaddress(String resaddress) {
		this.resaddress = resaddress;
	}
	public String getResemail() {
		return resemail;
	}
	public void setResemail(String resemail) {
		this.resemail = resemail;
	}
	public RestaurantInfo() {
	
	}
	
}
