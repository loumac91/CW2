package q4.redaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import q4.format.StringFormatter;
import q4.util.StringUtils;

public class TextRedactionWriter {
  
  public void writeRedactionsToPath(String outputAbsolutePath, ArrayList<String> lines, ArrayList<Redaction> redactions) throws FileNotFoundException, IOException {

    File outputFile = new File(outputAbsolutePath);
    OutputStream outputStream = new FileOutputStream(outputFile);
    
    Iterator<Redaction> sortedIterator = redactions.iterator();
    Redaction currentRedaction = null;
    int lineCounter = 1, linesLength = lines.size();

    while (lineCounter <= linesLength) {
      String line = lines.get(lineCounter - 1);

      if (currentRedaction == null) {
        currentRedaction = sortedIterator.hasNext() ? sortedIterator.next() : null;
      }
    
      while (currentRedaction != null && currentRedaction.getLineNumber() == lineCounter) {
        int startIndex = currentRedaction.getStartIndex();
        int endIndex = currentRedaction.getEndIndex();
        String a =  line.substring(0, startIndex);
        String redactionText = StringUtils.repeat("*", (endIndex - startIndex));
        String b = line.substring(endIndex, line.length());
        line = a + redactionText + b;
        currentRedaction = sortedIterator.hasNext() ? sortedIterator.next() : null;
      }

      outputStream.write(StringFormatter.formatWithLineSeparator(line).getBytes());
      lineCounter++;
    }

    outputStream.close();
  }
}