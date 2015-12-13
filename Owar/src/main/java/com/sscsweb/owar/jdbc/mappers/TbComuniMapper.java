package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sscsweb.owar.entities.TbComuni;

import org.springframework.jdbc.core.RowMapper;

public class TbComuniMapper implements RowMapper<TbComuni> {

	public TbComuni mapRow(ResultSet arg0, int arg1) throws SQLException {
		TbComuni tbComuni = new TbComuni();
		tbComuni.setCodice_catastale(arg0.getString("codice_catastale"));
		tbComuni.setCodice_istat(arg0.getInt("codice_istat"));
		tbComuni.setId(arg0.getInt("id"));
		tbComuni.setNome_comune(arg0.getString("nome_comune"));
		tbComuni.setSiglaprovincia_comune(arg0.getString("siglaprovincia_comune"));
		return tbComuni;
	}

}
