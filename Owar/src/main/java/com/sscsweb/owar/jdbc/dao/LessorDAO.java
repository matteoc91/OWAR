package com.sscsweb.owar.jdbc.dao;

import com.sscsweb.owar.entities.Lessor;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface LessorDAO {
	
	public ResponseMessage createLessor(Lessor lessor);
	public ResponseMessage getLessor(Integer userId);
	public ResponseMessage getLessorFromId(Integer lessorId);
	public ResponseMessage updateFeedback(double feedback, int feedback_number, Integer lessorId);

}
