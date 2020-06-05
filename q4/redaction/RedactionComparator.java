package q4.redaction;

import java.util.Comparator;

public class RedactionComparator implements Comparator<Redaction> {

  @Override
  public int compare(Redaction a, Redaction b) {

    if (a.lineNumber == b.lineNumber) {
      // If on same line, sort by startIndex
      return a.startIndex - b.startIndex;
    }

    // Otherwise sort by line number (ascending)
    return a.lineNumber - b.lineNumber;
  }  
}