package com.sscsweb.owar.entities;

import java.sql.Timestamp;

public class Office {

	private Integer id;
	private String address;
	private Integer home_number;
	private Timestamp date_begin;
	private Timestamp date_end;
	private Double daily_price;
	private String description;
	private Integer lessor_id;
	private Integer tbComuni_id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getHome_number() {
		return home_number;
	}

	public void setHome_number(Integer home_number) {
		this.home_number = home_number;
	}

	public Timestamp getDate_begin() {
		return date_begin;
	}

	public void setDate_begin(Timestamp date_begin) {
		this.date_begin = date_begin;
	}

	public Timestamp getDate_end() {
		return date_end;
	}

	public void setDate_end(Timestamp date_end) {
		this.date_end = date_end;
	}

	public Double getDaily_price() {
		return daily_price;
	}

	public void setDaily_price(Double daily_price) {
		this.daily_price = daily_price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLessor_id() {
		return lessor_id;
	}

	public void setLessor_id(Integer lessor_id) {
		this.lessor_id = lessor_id;
	}

	public Integer getTbComuni_id() {
		return tbComuni_id;
	}

	public void setTbComuni_id(Integer tbComuni_id) {
		this.tbComuni_id = tbComuni_id;
	}
	
}
