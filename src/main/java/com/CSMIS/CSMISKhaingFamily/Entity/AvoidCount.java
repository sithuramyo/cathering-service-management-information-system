package com.CSMIS.CSMISKhaingFamily.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "avoidcount")
public class AvoidCount {
@Id
private int id;
private String doorLogNo;
private String dates;
private String avoid;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDoorLogNo() {
	return doorLogNo;
}
public void setDoorLogNo(String doorLogNo) {
	this.doorLogNo = doorLogNo;
}
public String getDates() {
	return dates;
}
public void setDates(String dates) {
	this.dates = dates;
}
public String getAvoid() {
	return avoid;
}
public void setAvoid(String avoid) {
	this.avoid = avoid;
}
public AvoidCount() {
	
}


}
