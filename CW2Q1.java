import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    writeSortedNamesToOutputFile();

    printAlreadySorted();
  }

  private static void printExampleFromSpecification() {

    String[] example = new String[] { "Michael", "Zack", "Rachid" };

    sortAndPrint(example);
  }

  private static void writeSortedNamesToOutputFile() {

    String[] unsortedNames = getUnsortedNames();

    if (unsortedNames.length == 0) return;
    
    String[] sortedNames = BubbleSort.sort(unsortedNames);
    
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
  }

  // This can be debugged to show that only 1 pass is made through the array
  private static void printAlreadySorted() {
    String[] s = new String[] { "Louis", "Michael", "Tom", "Wayne", "Zack" };

    sortAndPrint(s);
  }

  private static void sortAndPrint(String[] array) {

    printArray("Before sorting:", array);

    String[] result = BubbleSort.sort(array);

    printArray("After sorting:", result);
  }

  private static void printArray(String printStatement, String[] array) {

    System.out.println(printStatement);

    for (int i = 0; i < array.length; i++) {
      System.out.print(i == array.length - 1 ? array[i] : String.format("%s, ", array[i]));
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