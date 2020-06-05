package q4.format;

import java.time.Duration;

import q4.constant.Strings;

public class StringFormatter {
  
  public static String formatException(Exception exception) {
    return baseFormat(Strings.EXCEPTION_FORMAT, exception.getClass().getName(), exception.getMessage());
  }

  public static String formatDurationToSeconds(Duration duration) {
    Double timeInSeconds = duration.toMillis() / 1000.0;
    return baseFormat(Strings.SECONDS_TIMESTAMP_FORMAT, timeInSeconds);
  }

  public static String formatTimeElapsedForAction(String action) {
    return baseFormat(Strings.TIME_ELAPSED_FOR_ACTION_FORMAT, action);
  }

  public static String formatWithLineSeparator(String line) {
    return baseFormat(Strings.WITH_LINE_SEPARATOR_FORMAT, line, System.lineSeparator());
  }

  public static String formatTotalRedactions(int totalRedactions) {
    return baseFormat(Strings.TOTAL_REDACTIONS_FORMAT, totalRedactions);
  }

  public static String formatTotalNumberOfLines(int numberOfLines) {
    return baseFormat(Strings.TOTAL_NUMBER_OF_LINES_FORMAT, numberOfLines);
  }

  public static String formatNumberOfUniqueWords(int numberOfUniqueWords) {
    return baseFormat(Strings.TOTAL_UNIQUE_NUMBER_OF_WORDS_FORMAT, numberOfUniqueWords);
  }

  private static String baseFormat(String format, Object... args) {
    return String.format(format, args);
  }
}