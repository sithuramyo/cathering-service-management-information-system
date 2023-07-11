package com.CSMIS.CSMISKhaingFamily.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "registercatering")
public class RegisterCatering {
	@Id
	private String doorLogNo;
	
	private String employeeName;
	
	private String dept;
	
	private String division;
	
	private String team;
	
	private String status;
	
	private String staffId;
	
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@OneToMany(targetEntity = RegisterDate.class,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "doorlognodates", referencedColumnName = "doorLogNo")
	private List<RegisterDate> dateList ;
	
	@OneToMany(targetEntity = AvoidMeatList.class,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "doorlognomeats", referencedColumnName = "doorLogNo")
	private List<AvoidMeatList> meatList;

	@Override
	public String toString() {
		return "RegisterCatering [doorLogNo=" + doorLogNo + ", dateList=" + dateList + ", meatList=" + meatList + "]";
	}

	public String getDoorLogNo() {
		return doorLogNo; 
	}

	public void setDoorLogNo(String doorLogNo) {
		this.doorLogNo = doorLogNo;
	}

	public List<RegisterDate> getDateList() {
		return dateList;
	}

	public void setDateList(List<RegisterDate> dateList) {
		this.dateList = dateList;
	}

	public List<AvoidMeatList> getMeatList() {
		return meatList;
	}

	public void setMeatList(List<AvoidMeatList> meatList) {
		this.meatList = meatList;
	}
}
