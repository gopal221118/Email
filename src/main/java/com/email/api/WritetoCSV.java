package com.email.api;

import java.util.List;

import com.opencsv.CSVWriter;

public class WritetoCSV {

	public static void witeROCBase(List<String> value, String cin, String gst, CSVWriter writer) {
		try
		{
		String rowdet[]= {value.get(0),value.get(1),value.get(2),cin,gst};
        writer.writeNext(rowdet);
	}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
