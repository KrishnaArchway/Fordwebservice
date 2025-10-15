package com.archway.util;

public class StringUtility {
  public static String processNullToEmpty(String input) {
    if (input == null || input.equalsIgnoreCase("null"))
      return ""; 
    return input;
  }
  
  public static boolean isStringBlank(String string) {
    return (string == null || string.trim().length() == 0 || string.trim().equalsIgnoreCase("null"));
  }
}
