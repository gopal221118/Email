package com.iadv.gst;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVWriter;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GSTDatatoMap {
    public static void main(String[] args) {
        String excelFilePath = args[0];
        HashMap<String, ArrayList<String>> gstMap = new HashMap<String, ArrayList<String>>();
        String[] header = {"FSSAI_No", "Company_Name", "Company_Address","Filling_Status","CIN", "GST"};
        try
        {
        CSVWriter writer = new CSVWriter(new FileWriter(args[2]));
        writer.writeNext(header);
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            DataFormatter dataFormatter = new DataFormatter();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ArrayList<String> values = new ArrayList<>();
                String gst = "";

                for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                    Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellValue = dataFormatter.formatCellValue(cell);
                    if (cn == row.getLastCellNum() - 1) {
                        gst = cellValue;
                    } else {
                        values.add(cellValue);
                    }
                }

                gstMap.put(gst, values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the size of the HashMap
        System.out.println("HashMap Size: " + gstMap.size());

        // Print the values of the HashMap
        for (Map.Entry<String, ArrayList<String>> entry : gstMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " -> Values: " + entry.getValue());
            ArrayList<String> templist=new ArrayList<String>();
            if(args[1].contains(","))
            {
            String years[]=args[1].split(",");
            for(int i=0;i<years.length;i++)
            {
            	String gstresp=GSTApiRequest.getFillingStatus((entry.getKey()).trim(), years[i].trim());
            	if (gstresp.contains("No records Found") && !gstresp.contains("filingStatus")) 
            	{
            		System.out.println(entry.getKey()+"-"+"No records Found");
            		templist.add("No records Found");

            	}
            	else
            	{
            	HashMap<String, ArrayList<String>> dataMap=JsonToHashMap.extractValues(gstresp);
            	for (Map.Entry<String, ArrayList<String>> entryx : dataMap.entrySet()) {
    				System.out.println("Key: " + entryx.getKey() + " -> Values: " + entryx.getValue());
    				if((entryx.getValue().get(entryx.getValue().size()-1)).equalsIgnoreCase("Filed"))
    						{
    					templist.add(entryx.getKey()+"-"+(entryx.getValue().get(entryx.getValue().size()-1)));
    						}
    				else
    				{
    					templist.add(entryx.getKey()+"-"+(entryx.getValue().get(entryx.getValue().size()-1)));
	
    				}
    			}
            	}	
            }
            
            }
            else
            {
                String years=args[1];
                String gstresp=GSTApiRequest.getFillingStatus((entry.getKey()).trim(), years);
                if (gstresp.contains("No records Found") && !gstresp.contains("filingStatus")) 
            	{
            		System.out.println(entry.getKey()+"-"+"No records Found");
            		templist.add("No records Found");
            	}
                else
                {
            	HashMap<String, ArrayList<String>> dataMap=JsonToHashMap.extractValues(gstresp);
            	for (Map.Entry<String, ArrayList<String>> entryx : dataMap.entrySet()) {
    				System.out.println("Key: " + entryx.getKey() + " -> Values: " + entryx.getValue());
    				if((entryx.getValue().get(entryx.getValue().size()-1)).equalsIgnoreCase("Filed"))
    						{
    					templist.add(entryx.getKey()+"-"+(entryx.getValue().get(entryx.getValue().size()-1)));
    						}
    				else
    				{
    					templist.add(entryx.getKey()+"-"+(entryx.getValue().get(entryx.getValue().size()-1)));
	
    				}
    			}
                }
            }
           //Write Here 
            WriteGSTDetails.writecompanydetails(entry.getValue(),entry.getKey(),templist,writer);
            
        }
        writer.close();
        }
        catch (Exception e) {
        	e.printStackTrace();
		}
       
    }
}
