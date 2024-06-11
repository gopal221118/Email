package com.email.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import com.email.api.MailAPI_Trigger;
import com.email.data.EmailData_Extraction;
import com.email.data.EmailID_List;
import com.email.html.Insurance_HTML;

public class Bulk_Email {
	public static void main(String[] args) {
        String excelFilePathx = args[0];
        HashMap<Integer, String> ins_mnp = EmailID_List.getMailNPWD(excelFilePathx);
        String excelFilePath = args[1]; // Change this to the path of your Excel file
        HashMap<Integer, ArrayList<String>> ins_data = EmailData_Extraction.readExcelToHashMap(excelFilePath);
        int totalrounds=ins_mnp.size();
        int count=0;
        int pnf=0;
        int maillmt=Integer.parseInt(args[2].trim());
        while(((count*maillmt)+1)<=(((totalrounds-2)*maillmt)+1))
        {
        	for(int i=(count*maillmt)+1;i<((count+1)*maillmt)+1;i++)
        	{
        		System.out.println("*********************************************************");

        		System.out.println(ins_mnp.get(count+1)+"--------------"+i);
        		System.out.println(ins_data.get(i));
        		String mailnpwd[]=ins_mnp.get(count+1).split("~");
        		String subject="Mandatory ShopKeeper Insurance for Food "+ins_data.get(i).get(1)+" situated at pincode: "+(((ins_data.get(i).get(4)).split(":")[1]).trim());
        		String result=MailAPI_Trigger.trigger_Mail(i, ins_data.get(i).get(0), mailnpwd[0].trim(), subject,mailnpwd[1].trim(),ins_data.get(i).get(2),ins_data.get(i).get(1),(((ins_data.get(i).get(3)).split(":")[1]).trim()));
        		if(result.contains("Sent"))
        		{
        			pnf=pnf+1;
        			System.out.println("Current Execution Status: "+pnf+"/"+i);
        			System.out.println(result);
        		}
        		else
        		{
        			pnf=pnf+0;
        			System.out.println("Current Execution Status: "+pnf+"/"+i);
        			System.out.println(result);
        		}
        		System.out.println("*********************************************************");
        	}
        	count=count+1;
        }	
	}
}