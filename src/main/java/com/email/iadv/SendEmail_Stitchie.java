package com.email.iadv;

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

public class SendEmail_Stitchie {

    public static void main(String[] args) {

        // Recipient's email ID
        String to = "abhishek@indiaadvocacy.com";

        // Sender's email ID and password
        final String from = "salesiadv@stitchie.in";
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
            message.setSubject("Important: Action Required");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // HTML content
            String htmlContent = Insurance_HTML.getMailHTML(from, password, host);
            messageBodyPart.setContent(htmlContent, "text/html");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set the HTML part
            multipart.addBodyPart(messageBodyPart);

            // Create the plain text part
            BodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("This is the plain text version of the email content.");

            // Add the plain text part
            multipart.addBodyPart(textBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Add headers to improve deliverability
            message.addHeader("X-Priority", "1 (Highest)");
            message.addHeader("X-Mailer", "MyApp Mailer");
            message.addHeader("Return-Path", from);
            message.addHeader("List-Unsubscribe", "<mailto:unsubscribe@stitchie.in>");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
            System.err.println("Error: " + mex.getMessage());
            if (mex.getCause() != null) {
                System.err.println("Cause: " + mex.getCause());
            }
        }
    }
}
