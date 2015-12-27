package com.sscsweb.owar.utilities;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class Chiper {

	private static BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	
	public static final String encryptPassword(String password) {
		String encryptedPassword = passwordEncryptor.encryptPassword(password);
		return encryptedPassword;
	}
	
	public static final Boolean checkPassword(String password, String encryptedPassword) {
		return passwordEncryptor.checkPassword(password, encryptedPassword);
	}
	
}
