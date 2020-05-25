package q1.swap;

public class SwapDescending extends SwapBase {

  public <T> Boolean shouldSwap(T a, T b) {
    return collator.compare(a, b) < 0;
  }
}