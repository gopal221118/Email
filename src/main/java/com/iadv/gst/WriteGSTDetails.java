package com.iadv.gst;

import java.util.ArrayList;

import com.opencsv.CSVWriter;

public class WriteGSTDetails {

	public static void writecompanydetails(ArrayList<String> value, String key, ArrayList<String> templist, CSVWriter writer) {
		try
		{
			String rowlist[]= {value.get(2),value.get(0),value.get(0),templist.toString(),value.get(3),key.trim()};
	        writer.writeNext(rowlist);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
