package q4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import q4.format.StringFormatter;
import q4.util.CharUtils;
import q4.util.StringUtils;

public class CW2Q4 {

  public static void main(String[] args) {
   
    File file = new File(System.getProperty("user.dir") + "\\q4\\warandpeace.txt");
    // File file = new File(System.getProperty("user.dir") + "\\q4\\small.txt");

    OutputStream outputStream = null;
    try (FileLineReader fileLineReader = new FileLineReader(new FileInputStream(file))) {

      File outputFile = new File(System.getProperty("user.dir") + "\\q4\\output.txt");
      
      outputStream = new FileOutputStream(outputFile);
      
      Instant start = Instant.now();
      
      LinkedHashMap<String, LinkedHashSet<Redaction>> redactionMap = new LinkedHashMap<String, LinkedHashSet<Redaction>>();

      ArrayList<String> lines = new ArrayList<String>();

      LinkedHashMap<String, LinkedHashSet<RedactionCandidate>> candidatesMap = new LinkedHashMap<String, LinkedHashSet<RedactionCandidate>>();

      HashSet<String> globalLowerCaseWords = new HashSet<String>();

      int ln = 1;
      while (fileLineReader.hasNext()) {
        String line = fileLineReader.next();
        lines.add(line);

        Boolean capturingLower = false, capturingUpper = false;

        StringBuilder wordBuilder = new StringBuilder();
        int i = 0, l = line.length(), captureStart = -1;
        while (i < l) {
          char character = line.charAt(i);

          if (Character.isLetter(character)) {
            Boolean isUpperCase = Character.isUpperCase(character);
            if (isUpperCase) {
              if (capturingUpper) {
                capturingUpper = false;
                captureStart = -1;
                wordBuilder.setLength(0);
              } else {
                capturingUpper = true;
                captureStart = i;
                wordBuilder.append(character);
              }
            } else {
              capturingLower = !capturingUpper;
              wordBuilder.append(character);
              
              if (!capturingLower && !capturingUpper) {
                captureStart = i;
              }
            }
          } else if ((Character.isWhitespace(character) && (capturingUpper || capturingLower)) || (capturingUpper || capturingLower)) {

            String word = wordBuilder.toString();
            if (word.length() > 1) {
              if (capturingUpper) {
                Boolean isPreceededByWord = isPreceededByWord(line, captureStart);
                if (isPreceededByWord) {
                  LinkedHashSet<Redaction> redactions = redactionMap.getOrDefault(word, new LinkedHashSet<Redaction>());
                  redactions.add(new Redaction(ln, captureStart, i));
                  redactionMap.put(word, redactions);
                } else {
                  wordBuilder.setCharAt(0, CharUtils.toLowerCase(wordBuilder.charAt(0)));
                  String key = wordBuilder.toString();
                  LinkedHashSet<RedactionCandidate> redactionCandidates = candidatesMap.getOrDefault(word, new LinkedHashSet<RedactionCandidate>());
                  redactionCandidates.add(new RedactionCandidate(word, ln, captureStart, i));
                  candidatesMap.put(key, redactionCandidates);
                } 
              } else {
                globalLowerCaseWords.add(word);
              }
            }

            capturingUpper = false;
            capturingLower = false;
            captureStart = -1;
            wordBuilder.setLength(0);
          }

          i++;
        }

        if ((capturingUpper || capturingLower)) {
          String word = wordBuilder.toString();
          if (word.length() > 1) {
            if (capturingUpper) {
              Boolean isPreceededByWord = isPreceededByWord(line, captureStart);
              if (isPreceededByWord) {
                LinkedHashSet<Redaction> redactions = redactionMap.getOrDefault(word, new LinkedHashSet<Redaction>());
                redactions.add(new Redaction(ln, captureStart, i));
                redactionMap.put(word, redactions);
              } else {
                wordBuilder.setCharAt(0, CharUtils.toLowerCase(wordBuilder.charAt(0)));
                String key = wordBuilder.toString();
                LinkedHashSet<RedactionCandidate> redactionCandidates = candidatesMap.getOrDefault(word, new LinkedHashSet<RedactionCandidate>());
                redactionCandidates.add(new RedactionCandidate(word, ln, captureStart, i));
                candidatesMap.put(key, redactionCandidates);
              } 
            } else {
              globalLowerCaseWords.add(word);
            }
          }

          capturingUpper = false;
          capturingLower = false;          
        }
        
        captureStart = -1;
        wordBuilder.setLength(0);
        ln++;
      }

      for (String lowerCaseWord : globalLowerCaseWords) {
        candidatesMap.remove(lowerCaseWord);
      }

      for (String candidateKey : candidatesMap.keySet()) {
        String normalizedKey = StringUtils.capitalise(candidateKey);
        LinkedHashSet<Redaction> redactions = redactionMap.getOrDefault(normalizedKey, new LinkedHashSet<Redaction>());
        redactions.addAll(candidatesMap.get(candidateKey));
        // sort
        redactionMap.put(normalizedKey, redactions);

        // if (redactions == null) {
        //   redactionMap.put(normalizedKey, candidatesMap.get(candidateKey));
        // } else {
        //   redactions.addAll(candidatesMap.get(candidateKey));
        //   // sort
        // }
      }


      // 21924 redactions
      // for (LinkedHashSet<Redaction> redactionsOfWord : redactionMap.values()) {
      //   Collections.sort(redactionsOfWord, ); TODO - CREATE Comparator<Redaction>
      // }

      // flatMap same as SelectMany
      // Map<String, List<Tag>> map = new HashMap<>();
      // Stream<Tag> stream = map.values().stream().flatMap(List::stream);

      Duration duration = Duration.between(start, Instant.now());
      double dur = duration.toMillis() / 1000.0;
      System.out.println(dur + "Seconds");

    } catch (FileNotFoundException fileNotFoundException) {
      System.out.println(StringFormatter.formatException(fileNotFoundException));
    } catch (IOException ioException) {
      System.out.println(StringFormatter.formatException(ioException));
    } finally {
      try {
        outputStream.close();
      } catch (IOException ioException2) {
        StringFormatter.formatException(ioException2);
      }
    }
  }

  private static Boolean isPreceededByWord(String line, int captureIndex) {

    // could be
    // {Start of sentence}[A-Z] and above line blank
    // “[A-Z]
    // . [A-Z] - in cases of ellipsis, there is inconsistent rulings, so they cannot be definitive
    // ? [A-Z]
    // ! [A-Z]

    // iterate up to index, capturing prec
    // StringBuilder sb = new StringBuilder();

    // for (int i = 0; i < captureIndex; i++) 
    //   sb.append(line.charAt(i));

    // String preceedingText = sb.toString();
    String preceedingText = line.substring(0, captureIndex);
    if (preceedingText.length() < 2) return false;

    int length = preceedingText.length();
    char preceedingCharacter = preceedingText.charAt(length - 1);
    if (Character.isWhitespace(preceedingCharacter)) {
      return Character.isLetterOrDigit(preceedingText.charAt(length - 2));
    }
    
    return false;
  }
}