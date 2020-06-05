package q4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class TestCases {
  public static void main(String[] args) {
          
    // String t1 = "Nicholas, having as usual exhausted two pairs of horses"; // return true
    // String t2 = "“Nicholas, will you come to Iogel’s? Please do!” said Natasha."; // return true
    // // 15
    // String t3 = "A minute later Sonya came in with a frightened, guilty, and scared"; // return false
    // // 6
    // String t4 = "fault. What’s he to do if he has such luck?... And it’s not my fault"; // true
    // // 46
    // // t4 again
    // // 25
    // String t5 = "quits... it can’t be!... And why is he doing this to me?” Rostov"; // true


    // Boolean r1 = isPreceededByWord(t1, 0);
    // Boolean r2 = isPreceededByWord(t2, 1);
    // Boolean r3 = isPreceededByWord(t3, 15);
    // Boolean r4 = isPreceededByWord(t4, 7);
    // Boolean r5 = isPreceededByWord(t4, 47);
    // Boolean r6 = isPreceededByWord(t5, 25);

    // ArrayList<String> lines = new ArrayList<String>();
    // ArrayList<String> result = new ArrayList<String>();
    // lines.add("It was in July, 1805, and the speaker was the well-known Anna Pavlovna");
    // lines.add("Scherer, maid of honor and favorite of the Empress Marya Fedorovna.");
    // lines.add("With these words she greeted Prince Vasili Kuragin, a man of high");
    // lines.add("rank and importance, who was the first to arrive at her reception. Anna");
    // lines.add("Pavlovna had had a cough for some days. She was, as she said, suffering");
    // lines.add("from la grippe; grippe being then a new word in St. Petersburg, used");
    // lines.add("only by the elite.");


    // Redaction r1 = new Redaction(1, 10, 10);
    // Redaction r2 = new Redaction(1, 15, 25);
    // Redaction r3 = new Redaction(1, 0, 10);
    // Redaction r4 = new Redaction(4, 11, 21);
    // Redaction r5 = new Redaction(3, 0, 10);

    // LinkedHashSet<Redaction> rs = new LinkedHashSet<Redaction>();
    // ArrayList<Redaction> ar = new ArrayList<>();
    // ar.add(r1);
    // ar.add(r2);
    // ar.add(r3);
    // ar.add(r4);
    // ar.add(r5);

    // Comparator<Redaction> rc = new RedactionComparator();

    // Collections.sort(ar, rc);

    // Iterator<Redaction> sortedIterator = ar.iterator();

    // int lineCounter = 1, linesLength = lines.size();
    // Redaction currentRedaction = null;
    // while (lineCounter <= linesLength) {
    //   String line = lines.get(lineCounter - 1);

    //   // get redacted line
    //   // if (currentRedaction == null) {
    //     if (currentRedaction == null) {
    //       currentRedaction = sortedIterator.hasNext() ? sortedIterator.next() : null;
    //     }
      
    //     int i = 0;
    //     while (currentRedaction != null && currentRedaction.lineNumber == lineCounter) {
    //       String a =  line.substring(i, currentRedaction.startIndex);
    //       String redactionText = "*".repeat(currentRedaction.endIndex - currentRedaction.startIndex);
    //       String b = line.substring(currentRedaction.endIndex, line.length());
    //       line = a + redactionText + b;
    //       i = currentRedaction.endIndex;
    //       currentRedaction = sortedIterator.hasNext() ? sortedIterator.next() : null;
    //     }
        
    //   // }

    //   result.add(line);
    //   lineCounter++;
    // }

    // String x = "";
  }
}