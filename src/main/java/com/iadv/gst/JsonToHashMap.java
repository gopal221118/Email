package com.iadv.gst;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonToHashMap {
	public static HashMap<String, ArrayList<String>> extractValues(String jsonString) {

		// Create a HashMap to store the extracted data
		HashMap<String, ArrayList<String>> dataMap = new HashMap<String, ArrayList<String>>();
		try {
			try {
				// Parse the JSON string
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode rootNode = objectMapper.readTree(jsonString);
				JsonNode filingStatusNode = rootNode.path("filingStatus").get(0);

				// Iterate over the filingStatus array
				for (JsonNode node : filingStatusNode) {
					String taxp = node.path("taxp").asText()+"-"+node.path("rtntype").asText();
					ArrayList<String> values = new ArrayList<>();
					values.add(node.path("fy").asText());
					values.add(node.path("mof").asText());
					values.add(node.path("dof").asText());
					values.add(node.path("arn").asText());
					values.add(node.path("status").asText());

					// Add the key-value pair to the HashMap
					dataMap.put(taxp, values);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Print the size of the HashMap
			System.out.println("HashMap Size: " + dataMap.size());

			// Print the values of the HashMap
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}

}
