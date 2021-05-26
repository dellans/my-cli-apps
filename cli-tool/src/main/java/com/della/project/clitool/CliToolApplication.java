package com.della.project.clitool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CliToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(CliToolApplication.class, args);


		Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            String input = sc.nextLine();
            consoleApp(input);
        }
	}
	
	public static void consoleApp(String input) {
		if(input.length() > 0) {
			int len = input.length();
			String command = input.contains("-t") && !input.contains("-o") ? input.substring(len-7, len) : input;
			String logPath = "";
			String outputPath = "";
			System.out.println(command);
        	if (command.equals("-t txt")) {
        		logPath = input.split(" ")[0];
        		outputPath = FilenameUtils.removeExtension(logPath);
            	List<String> line = new ArrayList<String>();
            	line = ReadFile.readLog(logPath);
            	GenerateFile.generateTXT(line, outputPath);
            } else if (command.equals("-t json")) {
            	logPath = input.split(" ")[0];
        		outputPath = FilenameUtils.removeExtension(logPath);
            	List<String> line = new ArrayList<String>();
            	line = ReadFile.readLog(logPath);
            	GenerateFile.generateJSON(line, outputPath);
            } else if (command.contains("-t") && !command.contains("txt") && !command.contains("json")) {
            	logPath = input.split(" ")[0];
        		outputPath = FilenameUtils.removeExtension(logPath);
            	List<String> line = new ArrayList<String>();
            	line = ReadFile.readLog(logPath);
            	GenerateFile.generateTXT(line, outputPath);
            } else if (input.contains("-o")) {
            	logPath = input.split(" ")[0];
            	String full_path = input.split(" ")[input.split(" ").length-1];
            	String extension = "";
            	int i = full_path.lastIndexOf('.');
            	int p = Math.max(full_path.lastIndexOf('/'), full_path.lastIndexOf('\\'));

            	if (i > p) {
            	    extension = full_path.substring(i+1);
            	}
            	
            	String filename = FilenameUtils.removeExtension(full_path);
            	System.out.println(extension);
            	System.out.println(filename);
            	
            	if(extension.equals("json")) {
            		List<String> line = new ArrayList<String>();
                	line = ReadFile.readLog(logPath);
                	GenerateFile.generateJSON(line, filename);
            	} else {
            		List<String> line = new ArrayList<String>();
                	line = ReadFile.readLog(logPath);
                	GenerateFile.generateTXT(line, filename);
            	}
            } else if (input.equals("-h")){
            	System.out.println("Helps : ");
            	System.out.println("-t json					: Convert log file to json file");
            	System.out.println("-t text					: Convert log file to text file");
            	System.out.println("-t						: Convert log file to default plain text file");
            	System.out.println("-o [path]/[filename]	: Convert & save log file to the selected path");
            } else {
            	System.out.println("'" + input +"' is not recognize as an internal command");
            }
        }
	}
}
