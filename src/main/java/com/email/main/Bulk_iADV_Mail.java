package com.email.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;

import com.email.api.MailStitchie_Trigger;
import com.email.api.MailiADV_Trigger;
import com.email.data.EmailData_Extraction;
import com.email.data.EmailID_List;
import com.email.html.Insurance_HTML;

public class Bulk_iADV_Mail {
	public static void main(String[] args) {
		String excelFilePathx = args[0];
		HashMap<Integer, String> ins_mnp = EmailID_List.getMailNPWD(excelFilePathx);
		System.out.println(ins_mnp);
		String excelFilePath = args[1]; // Change this to the path of your Excel file
		HashMap<Integer, ArrayList<String>> ins_data = EmailData_Extraction.readExcelToHashMap(excelFilePath);
		int totalrounds = ins_mnp.size();
		int count = 0;
		int mcount = 0;
		int pnf = 0;
		int maillmt = Integer.parseInt(args[2].trim());
		System.out.println(maillmt);
		while (((count * maillmt) + 1) <= (((totalrounds - 2) * maillmt) + 1)) {
			for (int i = (count * maillmt) + 1; i < ((count + 1) * maillmt) + 1; i++) {
				System.out.println("*********************************************************");
				System.out.println(ins_mnp.get(mcount + 1) + "--------------" + i);
				System.out.println("Count:" + i);
				System.out.println(ins_data.get(i));

				System.out.println(mcount + 1);

				String subject = "Mandatory ShopKeeper Insurance for Food " + ins_data.get(i).get(1)
						+ " situated at pincode: " + (((ins_data.get(i).get(4)).split(":")[1]).trim());

				String result = "";
				if ((ins_mnp.get(mcount + 1)).contains("@stitchie")) {
					result = MailStitchie_Trigger.trigger_Mail(i, ins_data.get(i).get(0), ins_mnp.get(mcount + 1),
							subject, ins_data.get(i).get(2), ins_data.get(i).get(1),
							(((ins_data.get(i).get(3)).split(":")[1]).trim()));
				} else if ((ins_mnp.get(mcount + 1)).contains("@indiaadvocacy")) {
					result = MailiADV_Trigger.trigger_Mail(i, ins_data.get(i).get(0), ins_mnp.get(mcount + 1), subject,
							ins_data.get(i).get(2), ins_data.get(i).get(1),
							(((ins_data.get(i).get(3)).split(":")[1]).trim()));
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
}
