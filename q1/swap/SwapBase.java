package q1.swap;

import java.util.Comparator;

import q1.StringComparator;

// Added so that the should swap could have different implementations

public abstract class SwapBase {
  
  protected static final Comparator<String> stringComparator = new StringComparator();

  public abstract Boolean shouldSwap(String a, String b);

  public String[] swap(String[] array, int index, String a, String b) {
    array[index] = b;
    array[index + 1] = a;

    return array;
  }
}