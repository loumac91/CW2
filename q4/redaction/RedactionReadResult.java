package q4.redaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class RedactionReadResult {
  
  private final HashSet<String> uniqueWords;
  private final LinkedHashMap<String, LinkedHashSet<RedactionCandidate>> candidatesMap;
  private final LinkedHashMap<String, LinkedHashSet<Redaction>> redactionsMap;
  private final ArrayList<String> allLines;

  public RedactionReadResult(
    HashSet<String> uniqueWords,
    LinkedHashMap<String, LinkedHashSet<RedactionCandidate>> candidatesMap,
    LinkedHashMap<String, LinkedHashSet<Redaction>> redactionsMap,
    ArrayList<String> allLines
  ) {
    this.uniqueWords = uniqueWords;
    this.candidatesMap = candidatesMap;
    this.redactionsMap = redactionsMap;
    this.allLines = allLines;
  }

  public HashSet<String> getUniqueWords() {
    return this.uniqueWords;
  }

  public LinkedHashMap<String, LinkedHashSet<RedactionCandidate>> getCandidateMap() {
    return this.candidatesMap;
  }

  public LinkedHashMap<String, LinkedHashSet<Redaction>> getRedactionMap() {
    return this.redactionsMap;
  }

  public ArrayList<String> getAllLines() {
    return this.allLines;
  }
}