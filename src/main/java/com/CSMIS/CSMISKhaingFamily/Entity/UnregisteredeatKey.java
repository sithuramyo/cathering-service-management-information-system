package com.CSMIS.CSMISKhaingFamily.Entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
@Embeddable
public class UnregisteredeatKey implements Serializable{
	
	private String date;
	
	private String doorlogno;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDoorlogno() {
		return doorlogno;
	}

	public void setDoorlogno(String doorlogno) {
		this.doorlogno = doorlogno;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoorlogKey)) return false;
        DoorlogKey that = (DoorlogKey) o;
        return Objects.equals(getDate(), that.getDate()) &&
               Objects.equals(getDoorlogno(), that.getDoorlogno());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getDoorlogno());
    }

}
