package com.sscsweb.owar.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sscsweb.owar.entities.TbProvince;

import org.springframework.jdbc.core.RowMapper;

public class TbProvinceMapper implements RowMapper<TbProvince> {

	public TbProvince mapRow(ResultSet arg0, int arg1) throws SQLException {
		TbProvince tbProvince = new TbProvince();
		tbProvince.setCapoluogo_provincia(arg0.getString("capoluogo_provincia"));
		tbProvince.setCodiceistat_provicnia(arg0.getString("codiceistat_provicnia"));
		tbProvince.setCodiceistatregione_provincia(arg0.getString("codiceistatregione_provincia"));
		tbProvince.setNome_provincia(arg0.getString("nome_provincia"));
		tbProvince.setSigla_provincia(arg0.getString("sigla_provincia"));
		return tbProvince;
	}

}
