package com.sscsweb.owar.entities;

public class OfficeImg {

	private Integer id;
	private byte[] img;
	private Integer office_id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public Integer getOffice_id() {
		return office_id;
	}

	public void setOffice_id(Integer office_id) {
		this.office_id = office_id;
	}
	
}
