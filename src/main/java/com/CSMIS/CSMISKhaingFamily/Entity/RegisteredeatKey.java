package com.CSMIS.CSMISKhaingFamily.Entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class RegisteredeatKey implements Serializable{
	
	private String dates;
	
	private String doorLogNo;

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getDoorLogNo() {
		return doorLogNo;
	}

	public void setDoorLogNo(String doorLogNo) {
		this.doorLogNo = doorLogNo;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoorlogKey)) return false;
        DoorlogKey that = (DoorlogKey) o;
        return Objects.equals(getDates(), that.getDate()) &&
               Objects.equals(getDoorLogNo(), that.getDoorlogno());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDates(), getDoorLogNo());
    }
}
