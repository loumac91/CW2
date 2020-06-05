package q4;

public class Redaction {
  
  protected final int lineNumber;
  protected final int startIndex;
  protected final int endIndex;

  public Redaction(int lineNumber, int startIndex, int endIndex) {
    this.lineNumber = lineNumber;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  public int getLineNumber() {
    return this.lineNumber;
  }

  public int getStartIndex() {
    return this.startIndex;
  }

  public int getEndIndex() {
    return this.endIndex;
  }
}