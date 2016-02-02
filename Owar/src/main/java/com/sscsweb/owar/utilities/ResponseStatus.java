package com.sscsweb.owar.utilities;

import java.util.HashMap;

public class ResponseStatus {

	public static final HashMap<Integer, String> STATUS_MESSAGE = new HashMap<Integer, String>();
	
	static {
		STATUS_MESSAGE.put(ResponseCode.SUCCESS, "Operation Success");
		STATUS_MESSAGE.put(ResponseCode.NO_ITEM_SELECTED, "No Item Selected");
		STATUS_MESSAGE.put(ResponseCode.DB_ACCESS_ERROR, "DB Access Error");
		STATUS_MESSAGE.put(ResponseCode.NOT_FOUND, "Not Found");
		STATUS_MESSAGE.put(ResponseCode.ALREADY_EXIST, "Already Exist");
		STATUS_MESSAGE.put(ResponseCode.NOT_VALID, "Not Valid");
		STATUS_MESSAGE.put(ResponseCode.WRONG_CREDENTIALS, "Wrong Credentials");
		STATUS_MESSAGE.put(ResponseCode.MAIL_SEND_FAILURE, "Failure to Send Email");
		STATUS_MESSAGE.put(ResponseCode.WS_EXCEPTION, "Exception Occurred in the Web Services");
		STATUS_MESSAGE.put(ResponseCode.IS_EMPTY, "Empty Set");
	}
	
}
