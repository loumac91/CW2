package q1.swap;

import java.text.Collator;

public abstract class SwapBase {
  
  protected static final Collator collator = Collator.getInstance();

  public abstract <T> Boolean shouldSwap(T a, T b);

  public <T> T[] swap(T[] array, int index, T a, T b) {
    array[index] = b;
    array[index + 1] = a;

    return array;
  }
}