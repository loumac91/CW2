package q4;

import java.io.FileNotFoundException;
import java.io.IOException;

import q4.constant.Strings;
import q4.format.StringFormatter;
import q4.redaction.RedactionResult;
import q4.redaction.TextRedactor;

public class CW2Q4 {

  public static void main(String[] args) {

    TextRedactor textRedactor = new TextRedactor();
    
    try {
      
      RedactionResult redactionResult = textRedactor.redactText(Strings.WAR_AND_PIECE_FULL_FILEPATH, Strings.OUTPUT_FULL_FILEPATH);

      System.out.println("REDACTION RESULTS:");
      System.out.println(StringFormatter.formatTotalRedactions(redactionResult.getTotalRedactions()));
      System.out.println(StringFormatter.formatTotalNumberOfLines(redactionResult.getRedactionReadResult().getAllLines().size()));
      System.out.println(StringFormatter.formatNumberOfUniqueWords(redactionResult.getRedactionReadResult().getUniqueWords().size()));
      System.out.println();

      System.out.println(StringFormatter.formatTimeElapsedForAction("overall redaction process"));
      System.out.println(StringFormatter.formatDurationToSeconds(redactionResult.getTotalElapsedRedactionTime()));
      System.out.println();

      System.out.println(StringFormatter.formatTimeElapsedForAction("reading redactions"));
      System.out.println(StringFormatter.formatDurationToSeconds(redactionResult.getElapsedReadTime()));
      System.out.println();

      System.out.println(StringFormatter.formatTimeElapsedForAction("evaluating redaction pass"));
      System.out.println(StringFormatter.formatDurationToSeconds(redactionResult.getElapsedSortTime()));
      System.out.println();

      System.out.println(StringFormatter.formatTimeElapsedForAction("writing redactions"));
      System.out.println(StringFormatter.formatDurationToSeconds(redactionResult.getElapsedWriteTime()));

    } catch (FileNotFoundException fileNotFoundException) {
      StringFormatter.formatException(fileNotFoundException);
    } catch (IOException ioException) {
      StringFormatter.formatException(ioException);
    }   
  }
}