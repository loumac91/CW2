import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import q1.BubbleSort;
import q1.constant.Strings;

public class CW2Q1 {

  // Q1. Implement a bubble sort algorithm for a list of names in Java.
  // e.g ["Michael", "Zack", "Rachid"] becomes ["Michael", "Rachid", "Zack"]

  public static void main(String[] args) {
    
    printExampleFromSpecification();
    writeSortedNamesToOutputFileAndPrint();
  }

  private static void printExampleFromSpecification() {

    String[] example = new String[] { "Michael", "Zack", "Rachid" };

    sortAndPrint(example);
  }

  private static void writeSortedNamesToOutputFileAndPrint() {

    String[] unsortedNames = getUnsortedNames();

    if (unsortedNames.length == 0) return;
    
    Instant sortBeginTime = Instant.now();
    String[] sortedNames = BubbleSort.sort(unsortedNames);
    Duration sortTime = Duration.between(sortBeginTime, Instant.now());

    try {
      File outputFile = new File(Strings.SORTED_NAMES_FULL_FILEPATH);
      OutputStream outputStream = new FileOutputStream(outputFile);

      for (int i = 0; i < sortedNames.length; i++) {
        String format = i == sortedNames.length - 1 ? "\"%s\"" : "\"%s\",";
        outputStream.write(String.format(format, sortedNames[i]).getBytes());
      }

      outputStream.close();
    } catch (IOException ioException) {
      System.out.println(Strings.COULD_NOT_WRTIE_SORTED_NAMES_FILE);
    }

    System.out.println(Strings.SORTED_NAMES_SUCCESS);
    System.out.println(Strings.SORTED_NAMES_FULL_FILEPATH);
    System.out.println();

    Double timeInSeconds = sortTime.toMillis() / 1000.0;
    System.out.println(String.format(Strings.SORTING_TIME_FORMAT, timeInSeconds));
  }

  private static void sortAndPrint(String[] array) {

    printArray(Strings.BEFORE_SORTING, array);

    String[] result = BubbleSort.sort(array);

    printArray(Strings.AFTER_SORTING, result);
  }

  private static void printArray(String printStatement, String[] array) {

    System.out.println(printStatement);

    for (int i = 0; i < array.length; i++) {
      System.out.print(i == array.length - 1 ? array[i] : String.format(Strings.NAME_SEPARATOR_FORMAT, array[i]));
    }

    System.out.println();
    System.out.println();
  }

  private static String[] getUnsortedNames() {
    Path namesFilePath = Paths.get(Strings.NAMES_FULL_FILEPATH);
    List<String> fileLines;
    try {
      fileLines = Files.readAllLines(namesFilePath);
    } catch (IOException ioException) {
      System.out.println(Strings.COULD_NOT_FIND_NAMES_FILE);
      return new String[0];
    }
    
    String namesList = fileLines.iterator().next();
    Pattern namePattern = Pattern.compile("([A-Z])\\w+");
    Matcher matcher = namePattern.matcher(namesList);
    List<String> names = new ArrayList<String>();
    while (matcher.find()) {
      names.add(matcher.group());
    }

    return names.toArray(new String[0]);
  }
}