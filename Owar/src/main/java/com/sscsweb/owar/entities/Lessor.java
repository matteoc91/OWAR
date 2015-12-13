package com.sscsweb.owar.entities;

public class Lessor {

	private Integer id;
	private Double feedback_lessor;
	private String piva;
	private Integer user_id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Double getFeedback_lessor() {
		return feedback_lessor;
	}

	public void setFeedback_lessor(Double feedback_lessor) {
		this.feedback_lessor = feedback_lessor;
	}

	public String getPiva() {
		return piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
}
