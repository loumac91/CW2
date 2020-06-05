package q4.redaction;

import java.time.Duration;

public class RedactionResult {
  
  private final Duration readTime;
  private final Duration sortTime;
  private final Duration writeTime;
  private final Duration totalRedactionTime;
  private final RedactionReadResult redactionReadResult;
  private final int totalRedactions;

  public RedactionResult(
    Duration readTime, 
    Duration sortTime, 
    Duration writeTime, 
    Duration totalRedactionTime,
    RedactionReadResult redactionReadResult,
    int totalRedactions
  ) {
    this.readTime = readTime;
    this.sortTime = sortTime;
    this.writeTime = writeTime;
    this.totalRedactionTime = totalRedactionTime;
    this.redactionReadResult = redactionReadResult;
    this.totalRedactions = totalRedactions;
  }

  public Duration getElapsedReadTime() {
    return this.readTime;
  }

  public Duration getElapsedSortTime() {
    return this.sortTime;
  }

  public Duration getElapsedWriteTime() {
    return this.writeTime;
  }

  public Duration getTotalElapsedRedactionTime() {
    return this.totalRedactionTime;
  }

  public RedactionReadResult getRedactionReadResult() {
    return this.redactionReadResult;
  }

  public int getTotalRedactions() {
    return this.totalRedactions;
  }
}