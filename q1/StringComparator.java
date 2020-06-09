package q1;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {

  // This is a naive implementation insofar as it doesn't account for locale differences (some languages have a non Unicode precendence of characters)
  // Java documentation suggests using collation, however the following approach covers the functionality needed

  @Override
  public int compare(String a, String b) {
    // 1. Get the last index that both strings have a value at
    int aLength = a.length();
    int bLength = b.length();
    int lastSharedIndex = Math.min(aLength, bLength) - 1;
    
    // 2. Iterate through each character up to length of the shortest string i.e. last shared index
    int i = 0;
    while (i <= lastSharedIndex) {
      char aChar = a.charAt(i);
      char bChar = b.charAt(i++);

      // 3. If any character is not the same (regardless of case) return the Unicode value difference between the two non matching characters
      if (isCaseInsensitiveDifference(aChar, bChar)) {
        // char is a primitive data type that also has an integer value based on the Unicode table
        // hence subtracting one from the other can determine precedence
        return aChar - bChar;
      }
    }

    // 4. If they are so far equal, return difference between their lengths
    return aLength - bLength;
  }

  private Boolean isCaseInsensitiveDifference(char a, char b) {
    return a != b
      && Character.toUpperCase(a) != Character.toUpperCase(b)
      && Character.toLowerCase(a) != Character.toLowerCase(b);
  }
}