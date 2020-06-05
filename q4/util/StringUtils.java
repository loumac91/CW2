package q4.util;

import java.util.Collections;

public class StringUtils {
  
  public static Boolean isNull(String s) {
    return s == null;
  }

  public static Boolean isNullOrEmpty(String s) {
    return isNull(s) || s.isEmpty();
  }

  public static Boolean isNullEmptyOrWhitespace(String s) {
    return isNullOrEmpty(s) || s.trim().isEmpty();
  }

  // Intended that this only does one word
  public static String capitalise(String s) {
    return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
  }

  public static String repeat(String s, int repeatCount) {
    String ss = String.join("", Collections.nCopies(repeatCount, s));
    return ss;
  }
}