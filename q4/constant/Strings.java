package q4.constant;

public class Strings {
  
  // FILES
  public static final String USER_DIRECTORY = System.getProperty("user.dir");
  public static final String WAR_AND_PIECE_RELATIVE_FILEPATH = "\\q4\\warandpeace.txt";
  public static final String WAR_AND_PIECE_FULL_FILEPATH = USER_DIRECTORY + WAR_AND_PIECE_RELATIVE_FILEPATH;
  public static final String OUTPUT_RELATIVE_FILEPATH = "\\q4\\output.txt";
  public static final String OUTPUT_FULL_FILEPATH = USER_DIRECTORY + OUTPUT_RELATIVE_FILEPATH;
  public static final String TEST_SAMPLE_RELATIVE_FILEPATH = "\\q4\\small.txt";
  public static final String TEST_SAMPLE_FULL_FILEPATH = USER_DIRECTORY + TEST_SAMPLE_RELATIVE_FILEPATH;

  // PRINT STATEMENTS
  public static final String EXCEPTION_FORMAT = "Exception {%s}: \"%s\"";
  public static final String SECONDS_TIMESTAMP_FORMAT = "%s seconds";
  public static final String TIME_ELAPSED_FOR_ACTION_FORMAT = "Time elapsed for %s: ";
  public static final String WITH_LINE_SEPARATOR_FORMAT = "%s%s";
  public static final String TOTAL_REDACTIONS_FORMAT = "%d - words redacted";
  public static final String TOTAL_NUMBER_OF_LINES_FORMAT = "%d - lines read";
  public static final String TOTAL_NUMBER_OF_UNIQUE_LOWER_CASE_WORDS_FORMAT = "%d - unique lower case words read";
  public static final String FILE_LOCATION_FORMAT = "%s can be found here: %s";
}