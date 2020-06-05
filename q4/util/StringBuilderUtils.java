package q4.util;

public class StringBuilderUtils {
  
  public static String toLowerCaseString(StringBuilder sb) {
    sb.setCharAt(0, CharUtils.toLowerCase(sb.charAt(0)));
    return sb.toString();
  }

  public static void reset(StringBuilder sb) {
    sb.setLength(0);
  }
}