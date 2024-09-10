package com.email.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Add {
	
	
	 
	public static void main(String args[]) throws IOException 
	{
		  List<String> lines = Collections.emptyList();
	        try {
	            lines = Files.readAllLines(
	                Paths.get("C:\\Users\\Superuser\\Desktop\\testx.txt"),
	                StandardCharsets.UTF_8);
	            System.out.println(lines);
	        }
	 
	        catch (IOException e) {
	 
	            // do something
	            e.printStackTrace();
	        }
		  
		
	}

	
}	
	

