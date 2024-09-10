package com.email.html;

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

public class ROC_HTML {

	public static String getMailHTML(HashMap<String, String> HTMLMap,String fyear) {
	    String htmlContent = "";
	    String din3="";
	    String din4="";
	    String din5="";
	    try {
	    	String firstpart = "<!DOCTYPE html>\r\n"
	    			+ "<html lang=\"en\">\r\n"
	    			+ "<body style=\"font-family: Arial, sans-serif; line-height: 1.6; color: #333; background-color: #f4f4f4; margin: 0; padding: 10px;\">\r\n"
	    			+ "\r\n"
	    			+ "    <div style=\"background: #fff; padding: 20px; border-radius: 8px; max-width: 600px; margin: auto; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\r\n"
	    			+ "        <h1 style=\"color: #0056b3; margin: 10px 0;\">Secure Your Business – Complete Your RoC Filing for FY 2023-2024 Today!</h1>\r\n"
	    			+ "        <p>Dear <strong>"+HTMLMap.get("Director_Name1")+"/"+HTMLMap.get("Director_Name2")+"</strong>,</p>\r\n"
	    			+ "        <p>Warm Greetings from India Advocacy! We hope this message finds you thriving and that your esteemed company <strong>"+HTMLMap.get("Company_LLP Name")+"</strong> (CIN: <strong>"+HTMLMap.get("CIN")+"</strong>) is on a path of continual success and prosperity.</p>\r\n"
	    			+ "        <h2 style=\"color: #0056b3; margin: 10px 0;\">Important Reminder: RoC Filing for FY 2023-2024</h2>\r\n"
	    			+ "        <p>Your <strong>RoC filing for the financial year "+fyear+"</strong> is pending! Delays are accumulating late fines daily, increasing your financial burden since the last compliance date issued by the Ministry of Corporate Affairs. Timely RoC filings are not just a formality—they are a mandatory compliance requirement for every registered company in India. Non-compliance can lead to substantial penalties and even the risk of directorship cancellation.</p>\r\n"
	    			+ "        \r\n"
	    			+ "        <h2 style=\"color: #0056b3; margin: 10px 0; text-align: center;\">Benefits of Timely Compliance</h2>\r\n"
	    			+ "        <table style=\"width: 100%; border-collapse: collapse; margin: 20px 0; font-size: 14px;\">\r\n"
	    			+ "            <tr>\r\n"
	    			+ "        <td>\r\n"
	    			+ "            <center><img src=\"https://i.imgur.com/1YV6GLE.png\" alt=\"Avoid Hefty Penalties\" style=\"width: 120px; height: 120px;\"></center>\r\n"
	    			+ "            <center><h3>Avoid Hefty Penalties</h3></center>\r\n"
	    			+ "            <center><p>Save your hard-earned money from unnecessary fines.</p></center>\r\n"
	    			+ "        </td>\r\n"
	    			+ "        <td>\r\n"
	    			+ "            <center><img src=\"https://i.imgur.com/mkLc80j.png\" alt=\"Secure Your Directorship\" style=\"width: 120px; height: 120px;\"></center>\r\n"
	    			+ "            <center><h3>Secure Your Directorship</h3></center>\r\n"
	    			+ "            <center><p>Protect your leadership position from potential cancellation.</p></center>\r\n"
	    			+ "        </td>\r\n"
	    			+ "    </tr>\r\n"
	    			+ "    <tr>\r\n"
	    			+ "        <td>\r\n"
	    			+ "            <center><img src=\"https://i.imgur.com/3N8FaWL.png\" alt=\"Peace of Mind\" style=\"width: 120px; height: 120px;\"></center>\r\n"
	    			+ "            <center><h3>Peace of Mind</h3></center>\r\n"
	    			+ "            <center><p>Ensure your business runs smoothly without legal hassles.</p></center>\r\n"
	    			+ "        </td>\r\n"
	    			+ "        <td>\r\n"
	    			+ "            <center><img src=\"https://i.imgur.com/qKu4eZE.png\" alt=\"Improved Business Credibility\" style=\"width: 120px; height: 120px;\"></center>\r\n"
	    			+ "            <center><h3>Improved Business Credibility</h3></center>\r\n"
	    			+ "            <center><p>Enhance your company’s credibility and trustworthiness.</p></center>\r\n"
	    			+ "        </td>\r\n"
	    			+ "    </tr>\r\n"
	    			+ "        </table>\r\n"
	    			+ "        \r\n"
	    			+ "        <h2 style=\"color: #0056b3; margin: 10px 0;\">We're pleased to offer our customized ROC services to <strong>"+HTMLMap.get("Company_LLP Name")+" for just ₹19999/- which includes:</strong></h2>\r\n"
	    			+ "        <table style=\"width: 100%; border-collapse: collapse; margin: 20px 0; font-size: 14px;\">\r\n"
	    			+ "            <tr>\r\n"
	    			+ "    <td style=\"border: 1px solid #ddd; padding: 8px; text-align: center;\">\r\n"
	    			+ "        <img src=\"https://i.imgur.com/BLPCdqU.png\" alt=\"Form AOC-04\" style=\"width: 120px; height: 120px;\">\r\n"
	    			+ "        <h3 style=\"color: #0056b3;\">Form AOC-04 Filing</h3>\r\n"
	    			+ "        <p>Preparation & Filing of Financials Related Annual Return.</p>\r\n"
	    			+ "    </td>\r\n"
	    			+ "    <td style=\"border: 1px solid #ddd; padding: 8px; text-align: center;\">\r\n"
	    			+ "        <img src=\"https://i.imgur.com/8Z3GuLM.png\" alt=\"Form MGT-07\" style=\"width: 120px; height: 120px;\">\r\n"
	    			+ "        <h3 style=\"color: #0056b3;\">Form MGT-07 Filing</h3>\r\n"
	    			+ "        <p>Preparation & Filing of Management Related Annual Return.</p>\r\n"
	    			+ "    </td>\r\n"
	    			+ "</tr>\r\n"
	    			+ "<tr>\r\n"
	    			+ "    <td style=\"border: 1px solid #ddd; padding: 8px; text-align: center;\">\r\n"
	    			+ "        <img src=\"https://i.imgur.com/yL3tg2B.png\" alt=\"DSC of Auditor\" style=\"width: 120px; height: 120px;\">\r\n"
	    			+ "        <h3 style=\"color: #0056b3;\">DSC of Auditor</h3>\r\n"
	    			+ "        <p>Preparation of Digital Signature Certificate for Auditors in Form AOC-04.</p>\r\n"
	    			+ "    </td>\r\n"
	    			+ "    <td style=\"border: 1px solid #ddd; padding: 8px; text-align: center;\">\r\n"
	    			+ "        <img src=\"https://i.imgur.com/r7DL03Q.png\" alt=\"Balance Sheet Preparation\" style=\"width: 120px; height: 120px;\">\r\n"
	    			+ "        <h3 style=\"color: #0056b3;\">Balance Sheet Preparation</h3>\r\n"
	    			+ "        <p>Preparation of Balance Sheet, Audit Report, Director's Report, & Financial Statements.</p>\r\n"
	    			+ "    </td>\r\n"
	    			+ "</tr>\r\n"
	    			+ "<tr>\r\n"
	    			+ "    <td style=\"border: 1px solid #ddd; padding: 8px; text-align: center;\">\r\n"
	    			+ "        <img src=\"https://i.imgur.com/wydHXTG.png\" alt=\"Director eKYC\" style=\"width: 120px; height: 120px;\">\r\n"
	    			+ "        <h3 style=\"color: #0056b3;\">Director eKYC</h3>\r\n"
	    			+ "        <p>Secure and seamless online identity verification for company directors.</p>\r\n"
	    			+ "    </td>\r\n"
	    			+ "    <td style=\"border: 1px solid #ddd; padding: 8px; text-align: center;\">\r\n"
	    			+ "        <img src=\"https://i.imgur.com/SYz3N18.png\" alt=\"Business Insurance\" style=\"width: 120px; height: 120px;\">\r\n"
	    			+ "        <h3 style=\"color: #0056b3;\">Business Insurance</h3>\r\n"
	    			+ "        <p>₹5,00,000 worth of Free Business Insurance.</p>\r\n"
	    			+ "    </td>\r\n"
	    			+ "</tr>\r\n"
	    			+ "\r\n"
	    			+ "        </table>\r\n"
	    			+ "        \r\n"
	    			+ "        <div style=\"text-align: center;\">\r\n"
	    			+ "            <a href=\"https://iadv.in/legal/compliance/\" style=\"display: inline-block; background-color: #0056b3; color: #fff; padding: 15px 30px; font-size: 16px; text-decoration: none; border-radius: 5px; font-weight: bold; text-align: center; margin: 10px 0; width: 100%; box-sizing: border-box;\">Complete Your RoC Filing Now</a>\r\n"
	    			+ "        </div>\r\n"
	    			+ "        <h3 style=\"color: #0056b3; margin: 10px 0;\">Why Choose India Advocacy?</h3>\r\n"
	    			+ "        <p>Our dedicated team is committed to ensuring that all your RoC filing requirements are met accurately and on time with minimal hassle. Partner with us to avoid penalties, secure your directorship, and maintain peace of mind.</p>\r\n"
	    			+ "        <p>Don't delay—secure your business future today. Click the button below to get started or contact us for further details.</p>\r\n"
	    			+ "        <p>If you have any questions or need assistance, you can contact us directly:</p>\r\n"
	    			+ "<div style=\"text-align: center;\">\r\n"
	    			+ "    <a href=\"https://wa.me/918062177355\" style=\"display: inline-block; background-color: #25D366; color: #fff; padding: 15px 30px; font-size: 16px; text-decoration: none; border-radius: 5px; font-weight: bold; text-align: center; margin: 10px 0; width: 100%; box-sizing: border-box;\">Chat on WhatsApp</a>\r\n"
	    			+ "    <br/>\r\n"
	    			+ "    <a href=\"tel:+918062177355\" style=\"display: inline-block; background-color: #0056b3; color: #fff; padding: 15px 30px; font-size: 16px; text-decoration: none; border-radius: 5px; font-weight: bold; text-align: center; margin: 10px 0; width: 100%; box-sizing: border-box;\">Call Us Now</a>\r\n"
	    			+ "</div>\r\n"
	    			+ "<h3 style=\"color: #0056b3;\">Company Master Data</h3>\r\n"
	    			+ "        <table style=\"width: 100%; border-collapse: collapse; margin: 20px 0;\">\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <th style=\"border: 1px solid #ddd; padding: 8px; background-color: #0056b3; color: #fff;\">Field</th>\r\n"
	    			+ "                <th style=\"border: 1px solid #ddd; padding: 8px; background-color: #0056b3; color: #fff;\">Details</th>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Whether Listed or not</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Whether_Listed_or_not")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Company Category</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Company_Category")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Date of Balance Sheet</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Date_of_Balance_Sheet")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Registered Address</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Registered_Address")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Company / LLP Name</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Company_LLP Name")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Date of last AGM</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Date_of_last_AGM")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Date of Incorporation</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Date_of_Incorporation")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Paid up Capital(Rs)</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">₹ "+String.valueOf((int) Double.parseDouble(HTMLMap.get("DIN_No.1")))+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">ROC Code</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("ROC_Code")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Email Id</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Email_Id")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "        </table>\r\n"
	    			+ "\r\n"
	    			+ "        <h3 style=\"color: #0056b3;\">Directors/Signatory Details</h3>\r\n"
	    			+ "        <table style=\"width: 100%; border-collapse: collapse; margin: 20px 0;\">\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <th style=\"border: 1px solid #ddd; padding: 8px; background-color: #0056b3; color: #fff;\">Field</th>\r\n"
	    			+ "                <th style=\"border: 1px solid #ddd; padding: 8px; background-color: #0056b3; color: #fff;\">Details</th>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">DIN No.1</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+String.valueOf((int) Double.parseDouble(HTMLMap.get("DIN_No.1")))+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Director Name 1</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Director_Name1")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Begin Date1</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Begin_Date1")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">DIN No.2</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+String.valueOf((int) Double.parseDouble(HTMLMap.get("DIN_No.2")))+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Director Name2</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Director_Name2")+"</td>\r\n"
	    			+ "            </tr>\r\n"
	    			+ "            <tr>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Begin Date2</td>\r\n"
	    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Begin_Date2")+"</td>\r\n"
	    			+ "            </tr>\r\n";
	    		
	    	
	    	
	        		
	        if(!(HTMLMap.get("DIN_No.3").equalsIgnoreCase("null")))
    		{
	         din3 = 		 "            <tr>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">DIN No.3</td>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+String.valueOf((int) Double.parseDouble(HTMLMap.get("DIN_No.3")))+"</td>\r\n"
		    			+ "            </tr>\r\n"
		    			+ "            <tr>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Director Name 3</td>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Director_Name3")+"</td>\r\n"
		    			+ "            </tr>\r\n"
		    			+ "            <tr>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Begin Date3</td>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Begin_Date3")+"</td>\r\n"
		    			+ "            </tr>\r\n";
    		}
	        if(!(HTMLMap.get("DIN_No.4").equalsIgnoreCase("null")))
    		{
	         din4 = 	"            <tr>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">DIN No.4</td>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+String.valueOf((int) Double.parseDouble(HTMLMap.get("DIN_No.4")))+"</td>\r\n"
		    			+ "            </tr>\r\n"
		    			+ "            <tr>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Director Name 4</td>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Director_Name4")+"</td>\r\n"
		    			+ "            </tr>\r\n"
		    			+ "            <tr>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Begin Date4</td>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Begin_Date4")+"</td>\r\n"
		    			+ "            </tr>\r\n";
    		}
	        if(!(HTMLMap.get("DIN_No.5").equalsIgnoreCase("null")))
    		{
	         din5 = 	 "<tr>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">DIN No.5</td>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+String.valueOf((int) Double.parseDouble(HTMLMap.get("DIN_No.5")))+"</td>\r\n"
		    			+ "            </tr>\r\n"
		    			+ "            <tr>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Director Name 5</td>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Director_Name5")+"</td>\r\n"
		    			+ "            </tr>\r\n"
		    			+ "            <tr>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">Begin Date5</td>\r\n"
		    			+ "                <td style=\"border: 1px solid #ddd; padding: 8px;\">"+HTMLMap.get("Begin_Date5")+"</td>\r\n"
		    			+ "            </tr>\r\n";
    		}
	        		String lastpart=	 "        </table>\r\n"
	    	    			+ "        <p>We look forward to assisting you in securing your compliance and protecting your business.</p>\r\n"
	    	    			+ "    \r\n"
	    	    			+ "        <p>Best Regards,<br/><strong>India Advocacy Team</strong></p>\r\n"
	    	    			+ "    \r\n"
	    	    			+ "       <p class=\"footer\">\r\n"
	    	    			+ "  This is a system-generated email. Please do not reply to this email. For any assistance, contact us via the options above.\r\n"
	    	    			+ "  <br>\r\n"
	    	    			+ "  <a href=\"#\" class=\"unsubscribe-button\" onclick=\"alert('You have successfully unsubscribed from our mailing list.'); return false;\">Unsubscribe</a>\r\n"
	    	    			+ "</p>\r\n"
	    	    			+ "</body>\r\n"
	    	    			+ "</html>\r\n"
	    	    			+ "";
     		
	        		htmlContent= firstpart+din3+din4+din5+lastpart;
	        System.out.println(htmlContent);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return htmlContent;
	}
	
	public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder camelCase = new StringBuilder();
        boolean nextCharUpperCase = false;

        for (char currentChar : input.toCharArray()) {
            if (Character.isSpaceChar(currentChar) || currentChar == '_') {
                nextCharUpperCase = true;
            } else {
                if (nextCharUpperCase) {
                    camelCase.append(Character.toUpperCase(currentChar));
                    nextCharUpperCase = false;
                } else {
                    camelCase.append(Character.toLowerCase(currentChar));
                }
            }
        }

        return camelCase.toString();
    }
	
	}


