package com.sscsweb.owar.utilities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

public class Chiper {

	private static BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	private static BasicTextEncryptor textEncryptor = null;
	private static Key key = null;
	
	static {
		textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(Constant.ENCRYPTION_PASSWORD);
		key = MacProvider.generateKey();
	}
	
	public static final String encryptPassword(String password) {
		String encryptedPassword = passwordEncryptor.encryptPassword(password);
		return encryptedPassword;
	}
	
	public static final Boolean checkPassword(String password, String encryptedPassword) {
		return passwordEncryptor.checkPassword(password, encryptedPassword);
	}
	
	public static final String encryptText(String text) {
		return Jwts.builder().setSubject(text).signWith(SignatureAlgorithm.HS512, key).compact();
		//return textEncryptor.encrypt(text);
	}
	
	public static final String decryptText(String text) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(text).getBody().getSubject();
		//return textEncryptor.decrypt(text);
	}
	
}
