package q4.redaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import q4.io.FileLineReader;
import q4.util.CharUtils;
import q4.util.StringBuilderUtils;

public class TextRedactionReader {
  
  public RedactionReadResult readRedactions(String inputAbsolutePath) throws FileNotFoundException, IOException {

    HashSet<String> uniqueLowerCaseWords = new HashSet<String>();
    LinkedHashMap<String, LinkedHashSet<RedactionCandidate>> candidatesMap = new LinkedHashMap<String, LinkedHashSet<RedactionCandidate>>();
    LinkedHashMap<String, LinkedHashSet<Redaction>> redactionsMap = new LinkedHashMap<String, LinkedHashSet<Redaction>>();
    ArrayList<String> lines = new ArrayList<String>();

    try (FileLineReader fileLineReader = new FileLineReader(new FileInputStream(new File(inputAbsolutePath)))) {
      
      int lineNumber = 1;
      StringBuilder wordBuilder = new StringBuilder();

      // 1. Iterate through each line 

      while (fileLineReader.hasNext()) {
        String line = fileLineReader.next();
        lines.add(line);

        Boolean capturingLower = false, capturingUpper = false;
        int lineIndex = 0, lineLength = line.length(), captureStart = -1;

        // 2. Iterate through each character

        while (lineIndex < lineLength) {
          char character = line.charAt(lineIndex);


          // 3, If is letter, then begin capture (either by upper or lower case)
          if (Character.isLetter(character)) {
            if (Character.isUpperCase(character)) {
              // Ignore consecutive capitals (somewhat opinionated)
              if (capturingUpper) { 
                capturingUpper = false;
                captureStart = -1;
                StringBuilderUtils.reset(wordBuilder);
              } else {
                capturingUpper = true;
                captureStart = lineIndex;
                wordBuilder.append(character);
              }
            } else {
              capturingLower = !capturingUpper;
              wordBuilder.append(character);
              
              if (!capturingLower && !capturingUpper) {
                captureStart = lineIndex;
              }
            }

            // 4. If whitespace or any other character, capture word
          } else if ((Character.isWhitespace(character) && (capturingUpper || capturingLower)) || (capturingUpper || capturingLower)) { 

            String word = wordBuilder.toString();
            if (word.length() > 1) {
              if (capturingUpper) {
                Boolean isPreceededByWord = isPreceededByWord(line, captureStart);
                if (isPreceededByWord) {
                  LinkedHashSet<Redaction> redactions = redactionsMap.getOrDefault(word, new LinkedHashSet<Redaction>());
                  redactions.add(new Redaction(lineNumber, captureStart, lineIndex));
                  redactionsMap.put(word, redactions);
                } else {
                  String key = StringBuilderUtils.toLowerCaseString(wordBuilder);
                  LinkedHashSet<RedactionCandidate> redactionCandidates = candidatesMap.getOrDefault(key, new LinkedHashSet<RedactionCandidate>());
                  redactionCandidates.add(new RedactionCandidate(word, lineNumber, captureStart, lineIndex));
                  candidatesMap.put(key, redactionCandidates);
                } 
              } else {
                uniqueLowerCaseWords.add(word);
              }
            }

            capturingUpper = false;
            capturingLower = false;
            captureStart = -1;
            StringBuilderUtils.reset(wordBuilder);
          }

          lineIndex++;
        }

        if ((capturingUpper || capturingLower)) {
          String word = wordBuilder.toString();
          if (word.length() > 1) {
            if (capturingUpper) {
              Boolean isPreceededByWord = isPreceededByWord(line, captureStart);
              if (isPreceededByWord) {
                LinkedHashSet<Redaction> redactions = redactionsMap.getOrDefault(word, new LinkedHashSet<Redaction>());
                redactions.add(new Redaction(lineNumber, captureStart, lineIndex));
                redactionsMap.put(word, redactions);
              } else {
                String key = StringBuilderUtils.toLowerCaseString(wordBuilder);
                LinkedHashSet<RedactionCandidate> redactionCandidates = candidatesMap.getOrDefault(key, new LinkedHashSet<RedactionCandidate>());
                redactionCandidates.add(new RedactionCandidate(word, lineNumber, captureStart, lineIndex));
                candidatesMap.put(key, redactionCandidates);
              } 
            } else {
              uniqueLowerCaseWords.add(word);
            }
          }

          capturingUpper = false;
          capturingLower = false;          
        }
        
        // 5. When finished reading line, reset capture index and stringbuilder
        captureStart = -1;
        StringBuilderUtils.reset(wordBuilder);
        lineNumber++;
      }

    } catch (FileNotFoundException fileNotFoundException) {
      throw fileNotFoundException;
    } catch (IOException ioException) {
      throw ioException;
    }

    return new RedactionReadResult(
      uniqueLowerCaseWords, 
      candidatesMap, 
      redactionsMap, 
      lines
    );    
  }

  // This function checks to see whether an index in a line is proceeded first by whitespace, then by a letter
  private Boolean isPreceededByWord(String line, int captureIndex) {

    String preceedingText = line.substring(0, captureIndex);
    if (preceedingText.length() < 2) return false;

    int length = preceedingText.length();
    if (Character.isWhitespace(preceedingText.charAt(length - 1))) {
      char preceedingCharacter = preceedingText.charAt(length - 2);
      return Character.isLetterOrDigit(preceedingCharacter) 
        || CharUtils.isCommaColonOrSemiColon(preceedingCharacter);
    }
    
    return false;
  }
}