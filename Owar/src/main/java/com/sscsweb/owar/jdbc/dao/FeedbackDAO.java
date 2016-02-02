package com.sscsweb.owar.jdbc.dao;

import com.sscsweb.owar.entities.Feedback;
import com.sscsweb.owar.utilities.ResponseMessage;

public interface FeedbackDAO {
	
	public ResponseMessage addFeedback(Feedback feedback);
	public ResponseMessage getFeedback(Integer officeId, Integer limit);

}
