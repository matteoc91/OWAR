package com.sscsweb.owar.utilities;

public interface ResponseCode {

	public static final int SUCCESS = 1;
	public static final int NO_ITEM_SELECTED = 0;
	public static final int DB_ACCESS_ERROR = -1;
	public static final int NOT_FOUND = -2;
	public static final int ALREADY_EXIST = -3;
	public static final int NOT_VALID = -4;
	public static final int WRONG_CREDENTIALS = -5;
	public static final int MAIL_SEND_FAILURE = -6;
	public static final int WS_EXCEPTION = -7;
	public static final int IS_EMPTY = -8;
	
}
