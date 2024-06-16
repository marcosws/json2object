package com.github.marcosws.json2object;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Json2Object {
	
	public static String getFullPathResource(String partialPathFile) {
		
		String path = FilenameUtils.separatorsToSystem(partialPathFile);
		URL resource = Json2Object.class.getClassLoader().getResource(path);
		URI uri = null;
		try {
			uri = resource.toURI();
		} 
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return new File(uri).toString();
		
	}
	
	public static void dataJsonToObject(Object object, String json){
		
		JSONObject jsonObject = (JSONObject) JSONValue.parse(json);
        List<Field> allFields = Arrays.asList(object.getClass().getDeclaredFields());
        for(Field f : allFields){
        	f.setAccessible(true);
        	try {
				f.set(object, jsonObject.get(f.getName()).toString());
			} 
        	catch (IllegalArgumentException e) {
        		e.printStackTrace();
        	}
        	catch (IllegalAccessException e) {
				e.printStackTrace();
			}
        }
        
	}
	
	public static String readFile(String filePath) {
		
		String line;
        String fileContent = "";
        String returnContent = "";
        BufferedReader in = null;

        try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
		} 
        catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} 
        catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		while(true){
			try {
				line = in.readLine();
				if(line == null)
					break;
				fileContent += line;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte ptext[] = fileContent.getBytes();
		try {
			returnContent = new String(ptext, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnContent;
		
	}

}
