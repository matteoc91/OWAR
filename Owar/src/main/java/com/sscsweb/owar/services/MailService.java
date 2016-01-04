package com.sscsweb.owar.services;

public interface MailService {

	public boolean sendMail(String from, String[] to, String body, String subject);
	
}
