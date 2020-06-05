package q4.format;

public class StringFormatter {
  
  public static String formatException(Exception exception) {
    return baseFormat("Exception {%s}: \"%s\"", exception.getClass().getName(), exception.getMessage());
  }

  private static String baseFormat(String format, Object... args) {
    return String.format(format, args);
  }
}