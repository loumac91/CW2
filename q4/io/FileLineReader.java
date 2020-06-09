package q4.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import q4.util.StringUtils;

// Class wraps BufferedReader to easily read/iterate through a file
// AutoCloseable is used to ensure file is automatically disposed of when no longer is used

public class FileLineReader implements Iterator<String>, AutoCloseable {

  private final String DEFAULT_CHARACTER_SET = "UTF-8";
  private final BufferedReader bufferedReader;

  private String currentLine = null;
  private Boolean endOfFile = false;

  public FileLineReader(FileInputStream fileInputStream) throws IOException {
    this.bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, this.DEFAULT_CHARACTER_SET));
  }

  @Override
  public boolean hasNext() {
    if (!StringUtils.isNull(this.currentLine)) {
      return true;
    }

    if (this.endOfFile) {
      return false;
    }

    try {
      String line = this.bufferedReader.readLine();
      if (StringUtils.isNull(line)) { // null string here means end of file
        this.endOfFile = true;
        return false;
      }

      // Cache the read line
      this.currentLine = line;
      return true;
    } catch (IOException ioException) {
      try {
        this.close();
      } catch (Exception e) { }

      ioException.printStackTrace();
      
      return false;
    }
  }

  @Override
  public String next() {
    if (!hasNext()) return null;

    // Read cached line, reset cache and return previous cache value
    String result = this.currentLine;
    this.currentLine = null;
    return result;
  }

  @Override
  public void close() throws IOException {
    this.endOfFile = true;
    this.currentLine = null;
    this.bufferedReader.close();
  }
}