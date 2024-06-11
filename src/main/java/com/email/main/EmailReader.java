package com.email.main;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.email.api.MailAPI_Read;
import com.email.data.EmailID_List;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class EmailReader {

	public static void main(String[] args) throws Exception {
		// Path to the Excel file with Gmail credentials
		String credentialsFilePath = args[0];
		// Path to the output Excel file
		String outputFilePath = args[1];
		System.out.println(credentialsFilePath);
		System.out.println(outputFilePath);

		try {
			// Read Gmail credentials
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Emails");

			// Create header row
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("Reciever");
			header.createCell(1).setCellValue("Subject");
			header.createCell(2).setCellValue("Body");
			header.createCell(3).setCellValue("Sender");

			int rowCount = 1;
			HashMap<Integer, String> mailnpwd = EmailID_List.getMailNPWD(credentialsFilePath);
			for (int i = 1; i < mailnpwd.size(); i++) {
				String mnp[] = mailnpwd.get(i).split("~");
				String email = mnp[0].trim();
				String password = mnp[1].trim();
				MailAPI_Read.fetchAndWriteEmails(email, password, outputFilePath, workbook, rowCount, sheet);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
