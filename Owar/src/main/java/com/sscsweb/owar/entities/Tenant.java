package com.sscsweb.owar.entities;

public class Tenant {

	private Integer id;
	private Double feedback_tenant;
	private Integer user_id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Double getFeedback_tenant() {
		return feedback_tenant;
	}

	public void setFeedback_tenant(Double feedback_tenant) {
		this.feedback_tenant = feedback_tenant;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
}
