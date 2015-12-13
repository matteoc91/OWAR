package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sscsweb.owar.entities.TbRegioni;

public class TbRegioniMapper implements RowMapper<TbRegioni> {

	public TbRegioni mapRow(ResultSet arg0, int arg1) throws SQLException {
		TbRegioni tbRegioni = new TbRegioni();
		tbRegioni.setCapoluogo_regione(arg0.getString("capoluogo_regione"));
		tbRegioni.setCodiceistat_regione(arg0.getString("codiceistat_regione"));
		tbRegioni.setNome_regione(arg0.getString("nome_regione"));
		return tbRegioni;
	}

}
