package com.email.api;

import java.io.UnsupportedEncodingException;
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

public class MailStitchie_Trigger {
	
	public static String trigger_Mail(int count, String tox, String fromx, String sub,String name,String licno,String address) {
		String comment = "";
		try {
			// Recipient's email ID
			String to = tox;

			// Sender's email ID
			final String from = fromx;
			final String password = "Advocacy@22112018s";

	        // SMTP server information
	        String host = "mail.stitchie.in";
	        int port = 465; // Use 587 for non-SSL

	        // Set system properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", String.valueOf(port));
	        properties.put("mail.smtp.auth", "true");

	        if (port == 465) {
	            properties.put("mail.smtp.ssl.enable", "true");
	            properties.put("mail.smtp.socketFactory.port", String.valueOf(port));
	            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        } else if (port == 587) {
	            properties.put("mail.smtp.starttls.enable", "true");
	        }

	        // Get the default Session object
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, password);
	            }
	        });

	        try {
	            // Create a default MimeMessage object
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header
	            try {
					message.setFrom(new InternetAddress(from, "India Advocacy"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	         // Set To: header field of the header
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