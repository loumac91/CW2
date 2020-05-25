package q1.swap;

public class SwapDescending extends SwapBase {

  public Boolean shouldSwap(String a, String b) {
    return collator.compare(a, b) < 0;
  }
}