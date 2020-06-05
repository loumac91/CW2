// package q4;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.OutputStream;
// import java.time.Instant;
// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.Iterator;
// import java.util.LinkedHashMap;
// import java.util.LinkedHashSet;
// import java.util.stream.Collectors;
// import java.util.stream.Stream;

// import q4.constant.Strings;
// import q4.format.StringFormatter;
// import q4.io.FileLineReader;
// import q4.redaction.Redaction;
// import q4.redaction.RedactionCandidate;
// import q4.redaction.RedactionComparator;
// import q4.util.CharUtils;
// import q4.util.StringBuilderUtils;
// import q4.util.StringUtils;

// public class Backup {

//   public static void main(String[] args) {

//     Instant overallTime = Instant.now();

//     // File file = new File(Strings.WAR_AND_PIECE_RELATIVE_FILEPATH);
//     File file = new File(System.getProperty("user.dir") + "\\q4\\small.txt");

//     OutputStream outputStream = null;
//     try (FileLineReader fileLineReader = new FileLineReader(new FileInputStream(file))) {


      
//       LinkedHashMap<String, LinkedHashSet<Redaction>> redactionMap = new LinkedHashMap<String, LinkedHashSet<Redaction>>();

//       ArrayList<String> lines = new ArrayList<String>();

//       LinkedHashMap<String, LinkedHashSet<RedactionCandidate>> candidatesMap = new LinkedHashMap<String, LinkedHashSet<RedactionCandidate>>();

//       HashSet<String> globalLowerCaseWords = new HashSet<String>();

//       Instant readTime = Instant.now();

//       int ln = 1;
//       while (fileLineReader.hasNext()) {
//         String line = fileLineReader.next();
//         lines.add(line);

//         StringBuilder wordBuilder = new StringBuilder();
//         Boolean capturingLower = false, capturingUpper = false;
//         int i = 0, l = line.length(), captureStart = -1;
//         while (i < l) {
//           char character = line.charAt(i);

//           if (Character.isLetter(character)) {
//             Boolean isUpperCase = Character.isUpperCase(character);
//             // Ignore - consecutive capitals (somewhat opinionated)
//             if (isUpperCase) {
//               if (capturingUpper) {
//                 capturingUpper = false;
//                 captureStart = -1;
//                 StringBuilderUtils.reset(wordBuilder);
//               } else {
//                 capturingUpper = true;
//                 captureStart = i;
//                 wordBuilder.append(character);
//               }
//             } else {
//               capturingLower = !capturingUpper;
//               wordBuilder.append(character);
              
//               if (!capturingLower && !capturingUpper) {
//                 captureStart = i;
//               }
//             }
//           } else if ((Character.isWhitespace(character) && (capturingUpper || capturingLower)) || (capturingUpper || capturingLower)) {

//             String word = wordBuilder.toString();
//             if (word.length() > 1) {
//               if (capturingUpper) {
//                 Boolean isPreceededByWord = isPreceededByWord(line, captureStart);
//                 if (isPreceededByWord) {
//                   LinkedHashSet<Redaction> redactions = redactionMap.getOrDefault(word, new LinkedHashSet<Redaction>());
//                   redactions.add(new Redaction(ln, captureStart, i));
//                   redactionMap.put(word, redactions);
//                 } else {
//                   String key = StringBuilderUtils.toLowerCaseString(wordBuilder);
//                   LinkedHashSet<RedactionCandidate> redactionCandidates = candidatesMap.getOrDefault(word, new LinkedHashSet<RedactionCandidate>());
//                   redactionCandidates.add(new RedactionCandidate(word, ln, captureStart, i));
//                   candidatesMap.put(key, redactionCandidates);
//                 } 
//               } else {
//                 globalLowerCaseWords.add(word);
//               }
//             }

//             capturingUpper = false;
//             capturingLower = false;
//             captureStart = -1;
//             StringBuilderUtils.reset(wordBuilder);
//           }

//           i++;
//         }

//         if ((capturingUpper || capturingLower)) {
//           String word = wordBuilder.toString();
//           if (word.length() > 1) {
//             if (capturingUpper) {
//               Boolean isPreceededByWord = isPreceededByWord(line, captureStart);
//               if (isPreceededByWord) {
//                 LinkedHashSet<Redaction> redactions = redactionMap.getOrDefault(word, new LinkedHashSet<Redaction>());
//                 redactions.add(new Redaction(ln, captureStart, i));
//                 redactionMap.put(word, redactions);
//               } else {
//                 String key = StringBuilderUtils.toLowerCaseString(wordBuilder);
//                 LinkedHashSet<RedactionCandidate> redactionCandidates = candidatesMap.getOrDefault(word, new LinkedHashSet<RedactionCandidate>());
//                 redactionCandidates.add(new RedactionCandidate(word, ln, captureStart, i));
//                 candidatesMap.put(key, redactionCandidates);
//               } 
//             } else {
//               globalLowerCaseWords.add(word);
//             }
//           }

//           capturingUpper = false;
//           capturingLower = false;          
//         }
        
//         captureStart = -1;
//         StringBuilderUtils.reset(wordBuilder);
//         ln++;
//       }

//       System.out.println("time to read");
//       System.out.println(StringFormatter.formatTimeSinceInstant(readTime));

//       Instant transformTime = Instant.now();

//       for (String lowerCaseWord : globalLowerCaseWords) {
//         candidatesMap.remove(lowerCaseWord);
//       }

//       for (String candidateKey : candidatesMap.keySet()) {
//         String normalizedKey = StringUtils.capitalise(candidateKey);
//         LinkedHashSet<Redaction> redactions = redactionMap.getOrDefault(normalizedKey, new LinkedHashSet<Redaction>());
//         redactions.addAll(candidatesMap.get(candidateKey));
//         redactionMap.put(normalizedKey, redactions);
//       }

//       Stream<Redaction> r = redactionMap.values().stream().flatMap(LinkedHashSet<Redaction>::stream);
//       Stream<Redaction> r2 = r.sorted(new RedactionComparator());
//       ArrayList<Redaction> sorteds = r2.collect(Collectors.toCollection(ArrayList::new));

//       System.out.println("time to transform");
//       System.out.println(StringFormatter.formatTimeSinceInstant(transformTime));

//       Instant redactionTime = Instant.now();
      
//       File outputFile = new File(Strings.OUTPUT_FULL_FILEPATH);
      
//       outputStream = new FileOutputStream(outputFile);

//       Iterator<Redaction> sortedIterator = sorteds.iterator();
//       Redaction currentRedaction = null;
//       int lineCounter = 1, linesLength = lines.size();

//       while (lineCounter <= linesLength) {
//         String line = lines.get(lineCounter - 1);

//         if (currentRedaction == null) {
//           currentRedaction = sortedIterator.hasNext() ? sortedIterator.next() : null;
//         }
      
//         while (currentRedaction != null && currentRedaction.getLineNumber() == lineCounter) {
//           int startIndex = currentRedaction.getStartIndex();
//           int endIndex = currentRedaction.getEndIndex();
//           String a =  line.substring(0, startIndex);
//           String redactionText = StringUtils.repeat("*", (endIndex - startIndex));
//           String b = line.substring(endIndex, line.length());
//           line = a + redactionText + b;
//           currentRedaction = sortedIterator.hasNext() ? sortedIterator.next() : null;
//         }


//         outputStream.write((line + System.lineSeparator()).getBytes());
//         lineCounter++;
//       }

//       System.out.println("time to redact");
//       System.out.println(StringFormatter.formatTimeSinceInstant(redactionTime));


//     } catch (FileNotFoundException fileNotFoundException) {
//       System.out.println(StringFormatter.formatException(fileNotFoundException));
//     } catch (IOException ioException) {
//       System.out.println(StringFormatter.formatException(ioException));
//     } finally {

//       System.out.println("total time");
//       System.out.println(StringFormatter.formatTimeSinceInstant(overallTime));

//       try {
//         outputStream.close();
//       } catch (IOException ioException2) {
//         StringFormatter.formatException(ioException2);
//       }
//     }
//   }

//   private static Boolean isPreceededByWord(String line, int captureIndex) {

//     // could be
//     // {Start of sentence}[A-Z] and above line blank
//     // “[A-Z]
//     // . [A-Z] - in cases of ellipsis, there is inconsistent rulings, so they cannot be definitive
//     // ? [A-Z]
//     // ! [A-Z]
//     String preceedingText = line.substring(0, captureIndex);
//     if (preceedingText.length() < 2) return false;

//     int length = preceedingText.length();
//     char preceedingCharacter = preceedingText.charAt(length - 1);
//     if (Character.isWhitespace(preceedingCharacter)) {
//       return Character.isLetterOrDigit(preceedingText.charAt(length - 2));
//     }
    
//     return false;
//   }
// }