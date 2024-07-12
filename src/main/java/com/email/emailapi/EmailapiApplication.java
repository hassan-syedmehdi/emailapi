package com.email.emailapi;

import java.util.Properties;



// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@SpringBootApplication
public class EmailapiApplication {

	public static void main(String[] args) {
		// SpringApplication.run(EmailapiApplication.class, args);
		System.out.println("Preparing to send message");

		String message = "Hello, this is message for security check";
		String subject = "Coders Area Confirmation";
		String to = "syed.r@turvo.com";
		String from = "hassan.syedmehdi@gmail.com";
		
		sendEmail(message,subject,to,from);

	}

	//this is reponsible for sending email
	private static void sendEmail(String message, String subject, String to, String from){
		//Variable for gmail
		String host = "smtp.gmail.com";

		//get the system properties
		Properties properties= System.getProperties();
		System.out.println("PROPERTIES" + properties);

		//setting important information to properties object

		//host set

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		//Step 1: to get the session object..
		Session session = Session.getInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("hassan.syedmehdi@gmail.com", "*****");
			}
		});

		session.setDebug(true);

		//Step 2:Compose the message
		MimeMessage m = new MimeMessage(session);

		try{
			//from email
			m.setFrom(from);

			//adding recipient mail
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
			m.setText(message);

			//send
			//Step 3: Send the message using Transport class
			Transport.send(m);
			
			System.out.println("Sent Success.......");

		}catch(Exception e){
			e.printStackTrace();
		}


	}

}
