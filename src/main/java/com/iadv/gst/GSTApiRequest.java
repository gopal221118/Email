package com.iadv.gst;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GSTApiRequest {
    public static String getFillingStatus(String gstno,String year) {
    	String resp="";
    	LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthDate = currentDate.minusMonths(1);
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM");
        String previousMonth = previousMonthDate.format(monthFormatter);
    	try
    	{
        // Set the base URI for the API
        RestAssured.baseURI = "https://services.gst.gov.in";

        // Define the request payload
        String requestBody = "{ \"gstin\": \""+gstno+"\", \"fy\": \""+year+"\" }";

        // Send the POST request
        Response response = given()
            	.config(RestAssuredConfig.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation()))
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Cookie", "TS01b8883c=0140752c73c584ad1638086a2af31cb6435666d9c0117eb2465bad599dc5a0fd62e1361d1711b9d72dfb9e904d0b60685622101d76; Lang=en; ak_bmsc=8077F1E4E6B10EE8EA5C96AB5ECE8175~000000000000000000000000000000~YAAQV/naF2I+srWQAQAAcXTH5BjUWL4tkNCedJtf5HEu4WMvbYzqfwJAqpVZ3rfnGg75dhO83LgrY9XCIwY1ir6/RX96w2EAMinmygktS3CLNiuVP1lW7JO9q3hJNedDFPTJgfWOeZYsVUXTMqdTYE0IP4iQTRO1AaqwyZMqZNSCgEbEEVp6Yf+Pr/82gYWhoeOHnBKMtByxgax8oqzlOvbHPO92KdlZ84xIB3AXGPPVNW1T0+r9I8gx0o2+8ZrXjPK20shtO9klcO6s3gOiTy6iaVNpacHvcyTjy9jqxbv6wgEYZaluM2wjhuOFSFMOgtl6UesP0hoYOloKYIcQcaFMJnFb+L2t+RbER9EewJFFILIgT0bmkmcmHFxZKM8E; TS01b8883c=0140752c731fd65d5b90ab8a922256a1a8e4f4e3860e5d3384b1e1d34a0b591977d55737c7a04243f736da751a023c8c8124d9fbd9")
                .header("Origin", "https://services.gst.gov.in")
                .header("Referer", "https://services.gst.gov.in/services/searchtp")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-origin")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
                .header("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .body(requestBody)
                .when()
                .post("/services/api/search/taxpayerReturnDetails")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Print the response
        resp=response.asString();
        System.out.println("Response: " + response.asString());
    	}
    	catch (Exception e) {
    		e.printStackTrace();
		}
		return resp;
    }
}

