package q4;

public class LineToken {
  
  private int lineNumber;
  private int lineIndex;

  public LineToken() { }

  public LineToken(int lineNumber, int lineIndex) {
    this.lineNumber = lineNumber;
    this.lineIndex = lineIndex;
  }

  public int getLineNumber() {
    return this.lineNumber;
  }

  public void setLineNumber(int lineNumber) {
    this.lineNumber = lineNumber;
  }

  public int getLineIndex() {
    return this.lineIndex;
  }

  public void setLineIndex(int lineIndex) {
    this.lineIndex = lineIndex;
  }
}