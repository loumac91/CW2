package q4.util;

public class CharUtils {
  
  public static char toLowerCase(char c) {
    return (char) (c | 32);
  }

  public static char toUpperCase(char c) {
    return (char) (c & 65503);
  }
}