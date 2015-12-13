package com.sscsweb.owar.entities;

import java.sql.Timestamp;

public class OfficeHasTenant {

	private Integer office_id;
	private Timestamp date_begin;
	private Timestamp date_end;
	private Integer tenant_id;
	
	public Integer getOffice_id() {
		return office_id;
	}
	
	public void setOffice_id(Integer office_id) {
		this.office_id = office_id;
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

	public Integer getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(Integer tenant_id) {
		this.tenant_id = tenant_id;
	}
	
}
