package com.email.api;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVWriter;

public class Base_ROC {

	 public static void main(String[] args) {
		 HashMap<Integer, List<String>> dataMap = ExcelToHashMap.readfromExcel(args[0]);
		 final CSVWriter writerx;
		 try
		 {
		 writerx = new CSVWriter(new FileWriter(args[1]));
		 String[] header = {"Company_Name", "Company_Address", "Food_No","CIN","GST"};
         writerx.writeNext(header);

		  dataMap.forEach((key, value) -> {
	            System.out.println("Row: " + key + " => " + value);
	            String cname=value.get(0).toLowerCase();
	            String cnamex=((((cname.replace("private", "")).replace("limited", "")).replace("(opc)", "")).replace("pvt", "")).replace("ltd", "");
	            String cnamey=(((cnamex.trim()).replace("&", "")).replace(".",""));
	            String ename[]=cnamey.split(" ");
	            String cxn="";
	            for(int i=0;i<ename.length;i++)
	            {
	            	if(((ename[i].trim()).length())==0)
	            	{
	            		
	            	}
	            	else
	            	{
	            		cxn=cxn+ename[i].trim()+"-";	
	            	}
	            	System.out.println((ename[i].trim())+"~"+(ename[i].trim()).length());
	            }
	            System.out.println(cxn.substring(0, cxn.length()-1));
	            ArrayList<String> prresp=PrivateAPI.getCINResponse(cxn.substring(0, cxn.length()-1));
	         // Print the contents of the ArrayList
	            ArrayList<String> cingst=new ArrayList<String>();
	            for (String text : prresp) {
	            	if(text.contains("CIN"))
	            	{
	            		String RCIN="";
	            		String CIN= (text.substring(text.indexOf("is")+2, text.length())).trim();
	            		if(CIN.contains(" "))
	            		{
	            	RCIN=CIN.substring(0,CIN.indexOf(" "));	
	            	cingst.add(RCIN);
	                System.out.println(RCIN);
	            		}
	            		else
	            		{
	            			RCIN=CIN;	
	            			cingst.add(RCIN);
	            		}
	            	}
	            	else if(text.contains("GST"))
	            			{
	            		String RGST="";
	            		String GST= (text.substring(text.indexOf("is")+2, text.length())).trim();
	            		if(GST.contains(" "))
	            		{
	            			RGST=GST.substring(0,GST.indexOf(" "));	
	            			cingst.add(RGST);
	                System.out.println(RGST);
	            		}
	            		else
	            		{
	            			RGST=GST;
	            			cingst.add(RGST);
	            		}
		                System.out.println(GST.substring(0,GST.indexOf(" ")));
	            			}
	            }
	            System.out.println(cingst);
	            if(cingst.size()==0)
	            {
	            	System.out.println("NO CIN|GST Found");
	            }
	            else if(cingst.size()==1)
	            {
	            	WritetoCSV.witeROCBase(value,cingst.get(0),"Null",writerx);
	            }
	            else if(cingst.size()==2)
	            {
	            	WritetoCSV.witeROCBase(value,cingst.get(0),cingst.get(1),writerx);
	            }
	        });
		  writerx.close();
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		}
	    }
	    
}
