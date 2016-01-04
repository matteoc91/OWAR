package com.sscsweb.owar.services;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Service
public class AmazonSESService implements MailService {

	public boolean sendMail(String from, String[] to, String body, String subject) {
		
		Destination destination = new Destination().withToAddresses(to);
		
		Content mailSubject = new Content().withData(subject);
		Content textBody = new Content().withData(body);
		Body bodyMsg = new Body().withText(textBody);
		
		Message message = new Message().withSubject(mailSubject).withBody(bodyMsg);
		
		SendEmailRequest request = new SendEmailRequest().withSource(from).withDestination(destination).withMessage(message);
		
		try {
			
			System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");
			AWSCredentials credentials = null;
			
			try {
				credentials = new ProfileCredentialsProvider().getCredentials();
			} catch(Exception e) {
				System.out.println(
						"Cannot load the credentials from the credential profiles file. " +
						"Please make sure that your credentials file is at the correct " +
						"location (~/.aws/credentials), and is in valid format."
						);
				System.out.println("Error Message: " + e.getMessage());
				return false;
			}
			
			AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);
			
			Region region = Region.getRegion(Regions.US_WEST_2);
			client.setRegion(region);
			
			client.sendEmail(request);
			System.out.println("Email sent!");
			
			return true;
			
		} catch(Exception e) {
			System.out.println("The email was not sent.");
			System.out.println("Error Message: " + e.getMessage());
		}	
		
		return false;
	}

}
