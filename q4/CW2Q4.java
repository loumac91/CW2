package q4;

import java.io.FileNotFoundException;
import java.io.IOException;

import q4.constant.Strings;
import q4.format.StringFormatter;
import q4.redaction.RedactionResult;
import q4.redaction.TextRedactor;

public class CW2Q4 {

  // Q4. Implement in Java that from a given block of text redacts all proper nouns
  // (i.e. a name used for an individual person, place, or organisation, spelled with
  // an initial capital letter).

  public static void main(String[] args) {

    TextRedactor textRedactor = new TextRedactor();
    
    try {

      // You can search through output with ([A-Z]{1}[a-z]+) regular expression
      // to capitalised words that were not redacted
      
      RedactionResult redactionResult = textRedactor.redactText(Strings.WAR_AND_PIECE_FULL_FILEPATH, Strings.OUTPUT_FULL_FILEPATH);

      System.out.println("REDACTION RESULTS:");
      System.out.println(StringFormatter.formatTotalRedactions(redactionResult.getTotalRedactions()));
      System.out.println(StringFormatter.formatTotalNumberOfLines(redactionResult.getRedactionReadResult().getAllLines().size()));
      System.out.println(StringFormatter.formatNumberOfUniqueLowerCaseWords(redactionResult.getRedactionReadResult().getUniqueLowerCaseWords().size()));
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
      System.out.println();

      System.out.println(StringFormatter.formatFileLocation("Output file", Strings.OUTPUT_FULL_FILEPATH));

    } catch (FileNotFoundException fileNotFoundException) {
      StringFormatter.formatException(fileNotFoundException);
    } catch (IOException ioException) {
      StringFormatter.formatException(ioException);
    }   
  }
}