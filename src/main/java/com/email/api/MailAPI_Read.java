package com.email.api;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MailAPI_Read {
	
	public static void fetchAndWriteEmails(String email, String password, String outputFilePath, Workbook workbook, int rowCount, Sheet sheet) throws Exception {
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");

		Session session = Session.getInstance(properties, null);
		Store store = session.getStore();
		store.connect("imap.gmail.com", email, password);

		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);

		// Define the date range (last 7 days)
		long millisIn7Days = 7 * 24 * 60 * 60 * 1000L;
		Date startDate = new Date(System.currentTimeMillis() - millisIn7Days);

		SearchTerm searchTerm = new ReceivedDateTerm(ReceivedDateTerm.GT, startDate);
		Message[] messages = inbox.search(searchTerm);

		
		for (Message message : messages) {
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(message.getFrom()[0].toString());
			row.createCell(1).setCellValue(message.getSubject());
			row.createCell(2).setCellValue(getTextFromMessage(message));
			row.createCell(3).setCellValue(email);

		}

		try (FileOutputStream fileOut = new FileOutputStream(outputFilePath)) {
			workbook.write(fileOut);
		}

		
		inbox.close(false);
		store.close();
	}

	private static String getTextFromMessage(Message message) throws Exception {
		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			String result = "";
			Multipart multipart = (Multipart) message.getContent();
			int count = multipart.getCount();
			for (int i = 0; i < count; i++) {
				BodyPart part = multipart.getBodyPart(i);
				if (part.isMimeType("text/plain")) {
					result = result + part.getContent().toString();
				} else if (part.isMimeType("text/html")) {
					String html = (String) part.getContent();
					result = result + org.jsoup.Jsoup.parse(html).text();
				}
			}
			return result;
		}
		return "";
	}

}
