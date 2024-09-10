package com.email.api;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.email.html.Insurance_HTML;
import com.email.html.ROC_HTML;

public class MailiADV_ROCTrigger {

	public static String trigger_Mail(int count, String tox, String fromx, String sub, HashMap<String, String> HTMLMap, String fyear) {
		String comment = "";
		try {
			// Recipient's email ID
			String to = tox;

			// Sender's email ID
			final String from = fromx;
	        final String password = "Advocacy@22112018s";

	        // SMTP server information
	        String host = "smtp.hostinger.com";
	        int port = 465;

	        // Set system properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", String.valueOf(port));
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.socketFactory.port", String.valueOf(port));
	        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

	        // Get the default Session object
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, password);
	            }
	        });

			try {
				// Create a default MimeMessage object
				MimeMessage message = new MimeMessage(session);

				// Set From: header field
				message.setFrom(new InternetAddress(from));

				// Set To: header field
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject(sub);
				  BodyPart messageBodyPart = new MimeBodyPart();

		            // Read the HTML content from the file
				  String htmlContent = ROC_HTML.getMailHTML(HTMLMap, fyear);

					// Set the content type to "text/html" and specify the character encoding
					messageBodyPart.setContent(htmlContent, "text/html; charset=utf-8");

					// Create a multipart message
					Multipart multipart = new MimeMultipart();

					// Add the HTML body part to the multipart message
					multipart.addBodyPart(messageBodyPart);

					// Set the content of the message to be the multipart
					message.setContent(multipart);

					// Send the message
					Transport.send(message);

				comment = "Mail No:" + count + "--" + "Sent mail successfully";
			} catch (MessagingException mex) {
				comment = "Mail No:" + count + "--" + "Mail sending failed";
				mex.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comment;
	}

}
