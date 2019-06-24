package com.crm.customers.beancreater.code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

public class GeneralCreator {
	public static void create(String srcfile, String encoder, String outfile, Map<String, String> map) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(srcfile), encoder));
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			sb.append(line).append(ClassCreator1.LINESEPARATOR);
		}
		reader.close();
		String text = sb.toString();
		for (String key: map.keySet()){
			text = text.replaceAll(key, map.get(key));
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile), encoder));
		bw.write(text);
		bw.close();
	}
	
	public static void writeFile(String file, String encoder, String content) throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoder));
		bw.write(content);
		bw.close();
		
	}
}