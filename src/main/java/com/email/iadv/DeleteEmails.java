package com.email.iadv;

import java.util.HashMap;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

import com.email.data.EmailID_List;

public class DeleteEmails {

    public static void main(String[] args) {

        // Email credentials
    	String excelFilePathx = args[0];
        HashMap<Integer, String> ins_mnp = EmailID_List.getMailNPWD(excelFilePathx);
        System.out.println(ins_mnp);
        
        for(int i=1;i<ins_mnp.size();i++)
        {
        	try
        	{
        final String username = ins_mnp.get(i);
        final String password = "Advocacy@22112018s";

        // IMAP server information
        String host = "imap.hostinger.com";

        // Set properties
        Properties properties = new Properties();
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.ssl.enable", "true");

        // Get the Session object
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create IMAP store object and connect to the store
            Store store = session.getStore("imap");
            store.connect(host, username, password);

            // Define the search term to find emails with the specific subject
            SearchTerm searchTerm = new SearchTerm() {
                @Override
                public boolean match(Message message) {
                    try {
                        return message.getSubject().equalsIgnoreCase("Undelivered Mail Returned to Sender");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            };

            // Delete emails from Inbox
            deleteEmailsFromFolder(store, "INBOX", searchTerm);

            // Delete emails from Sent
            deleteEmailsFromFolder(store, "Sent", searchTerm); // Adjust the folder name as needed

            // Delete emails from Junk
            deleteEmailsFromFolder(store, "Junk", searchTerm); // Adjust the folder name as needed

            store.close();

            System.out.println("All relevant emails deleted successfully for Email: "+username);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
        	}
        	catch (Exception e) {
        		System.out.println("Invalid Credentials:"+ ins_mnp.get(i));
			}
        }
    }

    private static void deleteEmailsFromFolder(Store store, String folderName, SearchTerm searchTerm) {
        try {
            Folder folder = store.getFolder(folderName);
            if (folder.exists()) {
                folder.open(Folder.READ_WRITE);
                Message[] messages = folder.search(searchTerm);
                for (Message message : messages) {
                    message.setFlag(javax.mail.Flags.Flag.DELETED, true);
                }
                folder.expunge();
                folder.close(true);
                System.out.println("Deleted emails from folder: " + folderName);
            } else {
                System.out.println("Folder not found: " + folderName);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error accessing folder: " + folderName);
        }
    }
}
