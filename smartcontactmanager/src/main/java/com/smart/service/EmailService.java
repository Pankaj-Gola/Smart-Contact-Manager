package com.smart.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	public boolean sendEmail(String subject, String message, String to) {

		// rest of the code
		boolean f = false;

		String from = "golapankaj07042003@gmail.com";

		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES " + properties);

//		boolean flag = false;

		properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.host", "smtp.gmail.com");

		String username = "golapankaj07042003@gmail.com";
		String password = "kiwimrtqtqwbrhsw";

		// session get
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		session.setDebug(true);

		MimeMessage m = new MimeMessage(session);

		try {

			// from email
			m.setFrom(from);

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
//			m.setText(message);
			m.setContent(message, "text/html");
			;

			Transport.send(m);

			System.out.println("Sent success.........");
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

}
