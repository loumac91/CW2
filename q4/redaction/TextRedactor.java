package q4.redaction;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import q4.util.StringUtils;

public class TextRedactor {
  
  private final TextRedactionReader redactionReader;
  private final TextRedactionWriter redactionWriter;

  public TextRedactor() {
    this.redactionReader = new TextRedactionReader();
    this.redactionWriter = new TextRedactionWriter();
  }

  public RedactionResult redactText(String inputAbsolutePath, String outputAbsolutePath) throws IOException {

    Instant overallTime = Instant.now();
    
    // 1. Read:
    // All unique words
    // All capitalised words that are not preceeded by whitespace and a letter
    // All capitalised words that are preceeded by whitespace and a letter
    // All lines of the file
    Instant readTimeStart = Instant.now();
    RedactionReadResult redactionReadResult = this.redactionReader.readRedactions(inputAbsolutePath);
    
    Duration readTime = Duration.between(readTimeStart, Instant.now());

    // 2. Combine and sort the redactions
    // Don't redact any word that appears in lower case throughout document
    Instant sortTimeStart = Instant.now();
    ArrayList<Redaction> sortedRedactions = getSortedRedactions(redactionReadResult);    

    Duration sortTime = Duration.between(sortTimeStart, Instant.now());

    // 3. Write redactions to output file
    // Using * to mask original words
    Instant writeTimeStart = Instant.now();
    redactionWriter.writeRedactionsToPath(outputAbsolutePath, redactionReadResult.getAllLines(), sortedRedactions);

    Duration writeTime = Duration.between(writeTimeStart, Instant.now());

    return new RedactionResult(
      readTime,
      sortTime,
      writeTime,
      Duration.between(overallTime, Instant.now()),
      redactionReadResult,
      sortedRedactions.size()
    );
  }

  // This function will return a single list of all the redactions to make on the output file
  // (ordered by occurrence in input file)
  private ArrayList<Redaction> getSortedRedactions(RedactionReadResult redactionReadResult) {

    // Remove any candidate for redaction that has a lower case occurrence in input file 
    LinkedHashMap<String, LinkedHashSet<RedactionCandidate>> candidatesMap = redactionReadResult.getCandidateMap();
    for (String uniqueWord : redactionReadResult.getUniqueLowerCaseWords()) {
      candidatesMap.remove(uniqueWord);
    }

    // Everything that is left is a proper noun, add them to redactions map
    LinkedHashMap<String, LinkedHashSet<Redaction>> redactionMap = redactionReadResult.getRedactionMap();
    for (String candidateKey : candidatesMap.keySet()) {
      String normalizedKey = StringUtils.capitalise(candidateKey);
      LinkedHashSet<Redaction> redactions = redactionMap.getOrDefault(normalizedKey, new LinkedHashSet<Redaction>());
      redactions.addAll(candidatesMap.get(candidateKey));
      redactionMap.put(normalizedKey, redactions);
    }

    // Transform redaction map into ArrayList<Redaction>
    return redactionMap
      .values() // Get the values
      .stream() // Convert to stream
      .flatMap(LinkedHashSet<Redaction>::stream) // Flatten structure to single collection of Redactions
      .sorted(new RedactionComparator()) // Sort with custom comparator
      .collect(Collectors.toCollection(ArrayList::new)); // And return as ArrayList
  }
}