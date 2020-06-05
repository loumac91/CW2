package q4.util;

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

  // TODO remove?

  // public static String[] split(String s, char delimiter) {    
  //   CharSequence[] temp = new CharSequence[(s.length() / 2) + 1];
  //   int i = 0, j = s.indexOf(delimiter, 0), wordCount = 0;
    
  //   while (j >= 0)
  //   {
  //       temp[wordCount++] = s.substring(i, j);
  //       i = j + 1;
  //       j = s.indexOf(delimiter, i); // rest of substrings
  //   }

  //   temp[wordCount++] = s.substring(i); // last substring

  //   String[] result = new String[wordCount];
  //   System.arraycopy(temp, 0, result, 0, wordCount);

  //   return result;
  // }
}