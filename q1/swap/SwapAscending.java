package q1.swap;

public class SwapAscending<T> extends SwapBase<T> {

  public Boolean shouldSwap(T a, T b) {
    return collator.compare(a, b) > 0;
  }
}