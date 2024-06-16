package com.github.marcosws.json2object;

import org.junit.jupiter.api.Test;

import com.github.marcosws.json2object.support.Client;
import com.github.marcosws.json2object.support.Utils;

import junit.framework.Assert;

public class Json2ObjectTest {
	
	
	@Test
	public void testGetFullPathResourcePathWindowsBar() {
		String path = Json2Object.getFullPathResource("\\data\\jsonfile.json");
		Assert.assertTrue("Erro ao validar", Utils.isNotNullOAndNotEmpty(path));
		System.out.println(path);
	}
	
	@Test
	public void testGetFullPathResourcePathLinuxBar() {
		String path = Json2Object.getFullPathResource("/data/jsonfile.json");
		Assert.assertTrue("Erro ao validar", Utils.isNotNullOAndNotEmpty(path));
		System.out.println(path);
	}
	
	@Test
	public void testReadFile() {
		String json = "{\r\n"
				+ "	\"codeId\":\"01\",\r\n"
				+ "	\"firstName\": \"Marcos Willian\",\r\n"
				+ "	\"lastName\": \"Souza\",\r\n"
				+ "	\"status\": \"OK\",\r\n"
				+ "	\"validation\": true,\r\n"
				+ "	\"number\": 123345\r\n"
				+ "}";
		
		String jsonOfFile = Json2Object.readFile(Json2Object.getFullPathResource("/data/jsonfile.json"));
		System.out.println(jsonOfFile);
		System.out.println(json.replace("\r\n", ""));
		Assert.assertEquals("Erro ao validar", json.replace("\r\n", ""), jsonOfFile);
	}
	
	@Test
	public void testDataJsonToObject() {
		
		Client client = new Client();
		String path = Json2Object.getFullPathResource("/data/jsonfile.json");
		String json = Json2Object.readFile(path);
		Json2Object.dataJsonToObject(client, json);
		
		System.out.println(client.getCodeId());
		System.out.println(client.getFirstName());
		System.out.println(client.getLastName());
		System.out.println(client.getStatus());
		System.out.println(client.getValidation());
		System.out.println(client.getNumber());
		
	}
	
	
	
	
	
	

}
