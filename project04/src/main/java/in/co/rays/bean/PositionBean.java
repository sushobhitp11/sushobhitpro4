package in.co.rays.bean;

import java.util.Date;

public class PositionBean extends BaseBean {
	
	private String designation;
	private Date openingDate;
	private String reqiredExperience;
	private String condition;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public String getReqiredExperience() {
		return reqiredExperience;
	}

	public void setReqiredExperience(String reqiredExperience) {
		this.reqiredExperience = reqiredExperience;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return designation;
	}

}
