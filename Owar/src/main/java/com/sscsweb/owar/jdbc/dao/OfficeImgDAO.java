package com.sscsweb.owar.jdbc.dao;

import com.sscsweb.owar.entities.OfficeImg;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface OfficeImgDAO {

	public ResponseMessage addImage(OfficeImg officeImg);
	public ResponseMessage getAllImage(Integer officeId);
	public ResponseMessage displayImage(Integer imgId);
	
}
