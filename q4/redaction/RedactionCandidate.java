package q4.redaction;

public class RedactionCandidate extends Redaction {

  private final String value;

  public RedactionCandidate(String value, int lineNumber, int startIndex, int endIndex) {
    super(lineNumber, startIndex, endIndex);
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}