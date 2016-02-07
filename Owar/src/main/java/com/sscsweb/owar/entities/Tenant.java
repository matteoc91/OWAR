package com.sscsweb.owar.entities;

import javax.persistence.Entity;

@Entity
public class Tenant {

	private Integer id;
	private Double feedback_tenant;
	private Integer user_id;
	private Integer feedback_number;
	
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

	public Integer getFeedback_number() {
		return feedback_number;
	}

	public void setFeedback_number(Integer feedback_number) {
		this.feedback_number = feedback_number;
	}
	
}
