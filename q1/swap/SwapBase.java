package q1.swap;

import java.text.Collator;

public abstract class SwapBase {
  
  protected static final Collator collator = Collator.getInstance();

  public abstract Boolean shouldSwap(String a, String b);

  public String[] swap(String[] array, int index, String a, String b) {
    array[index] = b;
    array[index + 1] = a;

    return array;
  }
}