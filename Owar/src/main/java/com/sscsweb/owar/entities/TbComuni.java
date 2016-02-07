package com.sscsweb.owar.entities;

import javax.persistence.Entity;

@Entity
public class TbComuni {

	private String nome_comune;
	private String siglaprovincia_comune;
	private String codice_catastale;
	private Integer codice_istat;
	private Integer id;
	
	public String getNome_comune() {
		return nome_comune;
	}
	
	public void setNome_comune(String nome_comune) {
		this.nome_comune = nome_comune;
	}

	public String getSiglaprovincia_comune() {
		return siglaprovincia_comune;
	}

	public void setSiglaprovincia_comune(String siglaprovincia_comune) {
		this.siglaprovincia_comune = siglaprovincia_comune;
	}

	public String getCodice_catastale() {
		return codice_catastale;
	}

	public void setCodice_catastale(String codice_catastale) {
		this.codice_catastale = codice_catastale;
	}

	public Integer getCodice_istat() {
		return codice_istat;
	}

	public void setCodice_istat(Integer codice_istat) {
		this.codice_istat = codice_istat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
