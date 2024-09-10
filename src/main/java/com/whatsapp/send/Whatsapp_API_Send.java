package com.whatsapp.send;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.email.iadv.WPExcelDataReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Whatsapp_API_Send {
   public static void main(String[] args) throws IOException {
      HashMap<Integer, ArrayList<String>> dataMap = WPExcelDataReader.readExcel(args[0]);
      String currentDate = WPExcelDataReader.getCurrentDate();
      System.out.println("Current date: " + currentDate);
      int count = 0;

      for(int i = 1; i < dataMap.size(); ++i) {
         System.out.println("********************************************");
         ArrayList<String> tempwpvals = (ArrayList)dataMap.get(i);
         System.out.println(tempwpvals);
         int statuscode;
        
            statuscode = triggerWhatsappMsg((String)tempwpvals.get(2), args[1], args[2], (String)tempwpvals.get(0), (String)tempwpvals.get(1), args[3], args[4]);
            System.out.println("Status Code:" + statuscode);
            if (statuscode != 200 && statuscode != 201) {
               count += 0;
               System.out.println("Message was not succesfully sent to: " + (String)tempwpvals.get(0));
               System.out.println("Total Exceution as of now: " + count + "/" + i);
            } else {
               ++count;
               System.out.println("Message succesfully sent to: " + (String)tempwpvals.get(0));
               System.out.println("Total Exceution as of now: " + count + "/" + i);
            }
             }

         System.out.println("********************************************");
         System.out.println("Total Execution in this run:" + count + "/" + (dataMap.size() - 1));
   }

   public static int triggerWhatsappMsg(String mobno, String templatename, String imglink, String name, String bname, String token,String tempcode) {
      int statuscode = 0;

      try {
         RestAssured.baseURI = "https://graph.facebook.com/v18.0";
         RequestSpecification request = RestAssured.given();
         request.header("Content-Type", "application/json", new Object[0]);
         request.header("Authorization", "Bearer " + token, new Object[0]);
         request.header("Cookie", "ps_l=0; ps_n=0", new Object[0]);
         String requestBody = "{\n"
         		+ "  \"messaging_product\": \"whatsapp\",\n"
         		+ "  \"recipient_type\": \"individual\",\n"
         		+ "  \"to\": \""+mobno+"\",\n"
         		+ "  \"type\": \"template\",\n"
         		+ "  \"template\": {\n"
         		+ "    \"name\": \""+templatename+"\",\n"
         		+ "    \"language\": {\n"
         		+ "      \"code\": \"en\"\n"
         		+ "    },\n"
         		+ "    \"components\": [\n"
         		+ "      {\n"
         		+ "        \"type\": \"header\",\n"
         		+ "        \"parameters\": [\n"
         		+ "          {\n"
         		+ "            \"type\": \"image\",\n"
         		+ "            \"image\": {\n"
         		+ "              \"link\": \""+imglink+"\"\n"
         		+ "            }\n"
         		+ "          }\n"
         		+ "        ]\n"
         		+ "      },\n"
         		+ "      {\n"
         		+ "        \"type\": \"body\",\n"
         		+ "        \"parameters\": [\n"
         		+ "          {\n"
         		+ "            \"type\": \"text\",\n"
         		+ "            \"text\": \""+name+"\"\n"
         		+ "          },\n"
         		+ "          {\n"
         		+ "            \"type\": \"text\",\n"
         		+ "            \"text\": \""+bname+"\"\n"
         		+ "          }\n"
         		+ "        ]\n"
         		+ "      }\n"
         		+ "    ]\n"
         		+ "  }\n"
         		+ "}";
        		 
        		 request.body(requestBody);
         Response response = (Response)request.post("/" + tempcode + "/messages", new Object[0]);
         statuscode += response.getStatusCode();
         System.out.println("Response body: " + response.getBody().asString());
      } catch (Exception var12) {
         var12.printStackTrace();
      }

      return statuscode;
   }
}
