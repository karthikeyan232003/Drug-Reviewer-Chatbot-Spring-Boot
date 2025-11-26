package com.example.pharmapulse.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.springframework.stereotype.Service;
@Service
public class SentimentService {

	public String analyze_sentiment(String str) throws IOException
	{
		ProcessBuilder builder = new ProcessBuilder("python",System.getProperty("user.dir")+"\\PythonScripts\\Sentiment.py");
		Process process = builder.start();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
		writer.write(str);
		writer.flush();
		writer.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		String sentiment1 = "";
		while((line=reader.readLine()) != null)
		{
			//System.out.println(line);
			sentiment1 = line;
			
		}
		return sentiment1;
		
	}
  
}
