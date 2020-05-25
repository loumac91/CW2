package q1.swap;

public class SwapDescending<T> extends SwapBase<T> {

  public Boolean shouldSwap(T a, T b) {
    return collator.compare(a, b) < 0;
  }
}