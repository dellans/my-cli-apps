package com.della.project.clitool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

	public static List<String> readLog(String path){
		List<String> log = new ArrayList<String>();
		
		try{
		   FileInputStream fstream = new FileInputStream(path);
		   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		   String strLine;
		   
		   /* read log line by line */
		   while ((strLine = br.readLine()) != null)   {
		     /* parse strLine to obtain what you want */
		     String line = strLine;
		     log.add(line);
		   }
		   fstream.close();
		} catch (Exception e) {
		     System.err.println("Error: " + e.getMessage());
		}
		
		return log;
	}
	
}
