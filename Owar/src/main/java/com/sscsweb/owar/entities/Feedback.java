package com.sscsweb.owar.entities;

public class Feedback {

	private Integer id;
	private Integer office_valuation;
	private String comment;
	private Integer office_id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOffice_valuation() {
		return office_valuation;
	}

	public void setOffice_valuation(Integer office_valuation) {
		this.office_valuation = office_valuation;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getOffice_id() {
		return office_id;
	}

	public void setOffice_id(Integer office_id) {
		this.office_id = office_id;
	}
	
}
