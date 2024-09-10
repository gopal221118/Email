package com.email.api;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.inject.spi.Elements;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PrivateAPI {
   
    
	public static ArrayList<String> getCINResponse(String companyname)
    {
    	String resp="";
    	 String value="";
         ArrayList<String> textList = new ArrayList<String>();

    	try
    	{
        // Create a request specification
        RequestSpecification request = RestAssured.given();

        // Set headers
        request.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        request.header("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8,hi;q=0.7");
        request.header("Cache-Control", "max-age=0");
        request.header("Connection", "keep-alive");
        request.header("Cookie", "_ga=GA1.2.338418090.1721762822; _gid=GA1.2.2110726886.1721762822; _clck=1nrb3ub%7C2%7Cfnp%7C0%7C1665; _clsk=1oa6uku%7C1721762871902%7C2%7C1%7Cz.clarity.ms%2Fcollect");
        request.header("Referer", "https://www.easyleadz.com/company/wonder-hospitality-and-breweries");
        request.header("Sec-Fetch-Dest", "document");
        request.header("Sec-Fetch-Mode", "navigate");
        request.header("Sec-Fetch-Site", "same-origin");
        request.header("Sec-Fetch-User", "?1");
        request.header("Upgrade-Insecure-Requests", "1");
        request.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
        request.header("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
        request.header("sec-ch-ua-mobile", "?0");
        request.header("sec-ch-ua-platform", "\"Windows\"");

        // Send GET request and get the response
        Response response = request.get("https://www.easyleadz.com/company/"+companyname+"/financials");

        // Print the response
        System.out.println("Response status code: " + response.getStatusCode());
        resp= response.getBody().asString();
        // Load the HTML file
		Document doc = Jsoup.parse(response.getBody().asString(), "UTF-8");

		// Select all <p itemprop="text"> elements
        org.jsoup.select.Elements paragraphs = doc.select("p[itemprop=text]");

        // Create an ArrayList to store the text

        // Extract text from each <p itemprop="text"> element and add it to the ArrayList
        for (Element paragraph : paragraphs) {
            textList.add(paragraph.text());
        }

        
    	}

    	catch (Exception e) {
    		e.printStackTrace();
    	}
		return textList;
		}
}

