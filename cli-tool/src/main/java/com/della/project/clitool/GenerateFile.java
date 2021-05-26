package com.della.project.clitool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenerateFile {
	
	public static void generateTXT(List<String> line, String outputPath) {
		try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath + ".txt"), "UTF-8")))
	    {
			for (String log : line){
				writer.write(log);
		        writer.write("\n");
			}
	    }
	    catch(IOException ex)
	    {
	        ex.printStackTrace();
	    }
	}
	
	public static void generateJSON(List<String> line, String outputPath) {
		
		List<Log> listLog = new ArrayList<Log>();
		for(String log: line) {
			//Split by " " for every line of log : [day] [date time] [server_name] [message]
			String[] splitted_log = log.split(" ");
			Log logToJson = new Log();
			logToJson.date = splitted_log[0] + " " + splitted_log[1] + " " + splitted_log[2] + " " + splitted_log[3];
			logToJson.server = splitted_log[4];
			String message = "";
			for(int i = 5; i < splitted_log.length; i++) {
				message = message + splitted_log[i] + " "; 
			}
			logToJson.message = message;
			listLog.add(logToJson);
		}
		    ObjectMapper mapper = new ObjectMapper();
		    String jsonString;
		    
			try {
				jsonString = mapper.writeValueAsString(listLog);
				// Writing to a file   
		        mapper.writeValue(new File(outputPath + ".json"), listLog );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	}
}
