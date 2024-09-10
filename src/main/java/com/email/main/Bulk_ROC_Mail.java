package com.email.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.email.api.MailStitchie_ROCTrigger;
import com.email.api.MailStitchie_Trigger;
import com.email.api.MailiADV_ROCTrigger;
import com.email.api.MailiADV_Trigger;
import com.email.data.EmailID_List;
import com.email.html.ROC_HTML;

public class Bulk_ROC_Mail {

	public static void main(String[] args) {
		try
		{
			HashMap<Integer,HashMap<String, String>> sheetMap=new HashMap<Integer,HashMap<String, String>>();
			String excelFilePathx = args[2];
			HashMap<Integer, String> ins_mnp = EmailID_List.getMailNPWD(excelFilePathx);
			System.out.println(ins_mnp);
			
		        // File path to the Excel file
		        String excelFilePath = args[0];
		        
		        // Create the main HashMap
		        HashMap<String, HashMap<String, String>> mainMap = new HashMap<>();

		        try (FileInputStream file = new FileInputStream(new File(excelFilePath));
		             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

		            // Get the first sheet
		            XSSFSheet sheet = workbook.getSheetAt(0);
		            Iterator<Row> rowIterator = sheet.iterator();

		            // Read the header row
		            Row headerRow = rowIterator.next();
		            int columnCount = headerRow.getLastCellNum();

		            // Iterate over each row
		            while (rowIterator.hasNext()) {
		                Row row = rowIterator.next();
		                String cin = row.getCell(0).getStringCellValue();
		                HashMap<String, String> rowMap = new HashMap<>();

		                // Iterate over each column in the row
		                for (int i = 0; i < columnCount; i++) {
		                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		                    String header = headerRow.getCell(i).getStringCellValue();
		                    String value = cell.toString().isEmpty() ? null : cell.toString();
		                    rowMap.put(header, value);
		                }

		                // Add the row map to the main map with CIN as the key
		                mainMap.put(cin, rowMap);
		            }
		            int cincount=0;
		            for (String key : mainMap.keySet()) {
			        	ArrayList<String> htmltablecontents=new ArrayList<String>();
			        	System.out.println("********************************************************************************************");
			            System.out.println("Key (CIN): " + key);
			            String email=((mainMap.get(key)).get("Email_Id"));
			            System.out.println("EMail:"+email);
			            htmltablecontents.add("CIN:"+key);
			            for (Map.Entry<String, String> entry : (mainMap.get(key)).entrySet()) {
			                String combinedEntry = entry.getKey() + ":" + entry.getValue();
			                htmltablecontents.add(combinedEntry);
			            }
			            HashMap<String, String> HTMLMap=new HashMap<String,String>();
			            for(int i=0;i<htmltablecontents.size();i++)
			            {
			            	String htmlarray[]=htmltablecontents.get(i).split(":");
			            	System.out.println(htmlarray[0]+"-------------------"+htmlarray[1]);
			            	HTMLMap.put(htmlarray[0].toString().trim(), htmlarray[1].toString().trim());
			            }
			            sheetMap.put((cincount), HTMLMap);
			            cincount++;
			        }
			        
			        int totalrounds = ins_mnp.size();
					int count = 0;
					int mcount = 0;
					int pnf = 0;
					int maillmt = Integer.parseInt(args[3].trim());
			        
					System.out.println(maillmt);
					while (((count * maillmt) + 1) <= (((totalrounds - 2) * maillmt) + 1)) {
						for (int i = (count * maillmt) + 1; i < ((count + 1) * maillmt) + 1; i++) {
							System.out.println("*********************************************************");
							System.out.println(ins_mnp.get(mcount + 1) + "--------------" + i);
							System.out.println("Count:" + i);
							System.out.println(sheetMap.get(i));

							System.out.println(mcount + 1);

							String subject = "Mandatory ROC Filing for FY -"+ args[1] + " for "+ sheetMap.get(i).get("Company_LLP Name")
									+ " holding CIN: " + (sheetMap.get(i).get("CIN"));

							String result = "";
							if ((ins_mnp.get(mcount + 1)).contains("@stitchie")) {
								result = MailStitchie_ROCTrigger.trigger_Mail(i,sheetMap.get(i).get("Email_Id") , ins_mnp.get(mcount + 1),
										subject, sheetMap.get(i),args[1]);
							} else if ((ins_mnp.get(mcount + 1)).contains("@indiaadvocacy")) {
								result = MailiADV_ROCTrigger.trigger_Mail(i,sheetMap.get(i).get("Email_Id") , ins_mnp.get(mcount + 1),
										subject, sheetMap.get(i),args[1]);
							} else {
								System.out.println((ins_mnp.get(mcount + 1)));
							}

							if (result.contains("Sent")) {
								pnf = pnf + 1;
								System.out.println("Current Execution Status: " + pnf + "/" + i);
								System.out.println(result);
							} else {
								pnf = pnf + 0;
								System.out.println("Current Execution Status: " + pnf + "/" + i);
								System.out.println(result);
							}
							System.out.println("*********************************************************");

							System.out.println("Size:" + ins_mnp.size());
							System.out.println("Limit:" + maillmt);
							System.out.println("Mcount:" + mcount);
							System.out.println("OOOOOOOOOOOOO:" + ((ins_mnp.size() - 1) * maillmt));
							if (mcount == (ins_mnp.size() - 2)) {
								System.out.println("Check");
								mcount = 0;
							}
							mcount = mcount + 1;
							count = count + 1;
							if (i == ((ins_mnp.size() - 1) * maillmt)) {
								break;
							}
						}
					}
		}
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	}
}
		     

