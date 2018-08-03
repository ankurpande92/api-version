package com.api.version;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ApiVersion {
    public static void processFile(ArrayList<String> array) {
    	
    	// First, Create a map of <API_Name, API_Version> to store latest version for each API.
    	Map<String, String> map = new HashMap<>();
    	
    	// 
    	Set<String> uniqueAppName = new HashSet<>();
    	
    	for (String string : array) {
    		// Split the string based on comma delimiter
			String[] str = string.split(", ");
			// add API_Name and version details to map for each string			
			map.put(str[1], str[2]);
		}
    	
    	// Second, Iterate over the each line again to find out older version for Each APP Name
    	for (String string : array) {
			String[] str = string.split(", ");
			
			// get the latest version from map for each API name
			String  mapVersion  = map.get(str[1]);	
			
			
			int latestVerionFromMap = Integer.parseInt(mapVersion.substring(1,mapVersion.length()));
			
			// get the input version name 
			String eachInputVersion = str[2];
			int versionFromInput =  Integer.parseInt(eachInputVersion.substring(1,eachInputVersion.length()));
			
			// compare the versions and add all the unique App name to the set
			if (versionFromInput < latestVerionFromMap){
				uniqueAppName.add(str[0]);
			}
		}
    	
    	System.out.println(uniqueAppName);
   }
   public static void main (String[] args) {
	   // Read file line-by-line
        ArrayList<String> inputData = new ArrayList<>();
        try {
            Scanner in = new Scanner(new BufferedReader(new FileReader("api-version.txt")));
            while(in.hasNextLine()) {
                String line = in.nextLine().trim();
                if (!line.isEmpty()) // Ignore blank lines
                    inputData.add(line);
            } 
            in.close();
            // Process file input
            processFile(inputData);
        } catch (IOException e) {
            System.out.println("IO error in input.txt");
        }
    }
}
