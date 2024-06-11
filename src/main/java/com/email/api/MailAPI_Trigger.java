package com.email.api;

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

public class MailAPI_Trigger {

	public static String trigger_Mail(int count, String tox, String fromx, String sub, String appwd,String name,String licno,String address) {
		String comment = "";
		try {
			// Recipient's email ID
			String to = tox;

			// Sender's email ID
			String from = fromx;

			// SMTP server information
			String host = "smtp.gmail.com";

			// Get system properties
			Properties properties = System.getProperties();

			// Setup mail server
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			// Get the Session object
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					// Use the app password here
					return new PasswordAuthentication(fromx, appwd);

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

				// Create the message part
				BodyPart messageBodyPart = new MimeBodyPart();

				// Now set the actual message

				String htmlContent = Insurance_HTML.getMailHTML(name, licno, address);
				messageBodyPart.setContent(htmlContent, "text/html");

				// Create a multipart message
				Multipart multipart = new MimeMultipart();

				// Set text message part
				multipart.addBodyPart(messageBodyPart);

				// Send the complete message parts
				message.setContent(multipart);

				// Send message
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
