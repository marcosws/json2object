package com.github.marcosws.json2object.support;

public class Utils {
	
	public static boolean isNullOrEmpty(String value) {
		return (value == null || value == "");
	}
	
	public static boolean isNotNullOAndNotEmpty(String value) {
		return (value != null || value != "");
	}

}
