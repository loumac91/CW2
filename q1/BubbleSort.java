package q1;

import q1.swap.SwapBase;
import q1.swap.SwapAscending;
import q1.swap.SwapDescending;

public class BubbleSort {
  
  public static String[] sort(String[] stringArray) {
    return sort(stringArray, new SwapAscending());
  }

  public static String[] sort(String[] stringArray, SortOrder sortOrder) {
    switch (sortOrder) {
      case DESCENDING: 
        return sort(stringArray, new SwapDescending());
      default:
        return sort(stringArray, new SwapAscending());
    }
  }

  // Outer loop, goes through each index i.e. 0 => last index
  // Each loop compares each value in the array left to right
  // Each loop guarantees that atleast one value will reach its sorted state on the right hand side of the array, if it's not already sorted
  // The inner loop therefore has the end (condition array.length - 1 - i) as the right move values should be sorted with every outer loop

  // The inner loop will compare two pairs of values and determine whether should be swapped 
  // If they should be swapped, then they swapped within the same array
  // All values are iterated through
  private static String[] sort(String[] array, SwapBase swapper) {
    for (int i = 0; i < array.length - 1; i++) { // < rather than <= to avoid out of range exceptions (we access the next value in the array with + 1)
      for (int j = 0; j < array.length - 1 - i; j++) {
        String a = array[j];
        String b = array[j + 1];
        if (swapper.shouldSwap(a, b)) {
          swapper.swap(array, j, a, b);
        }
      }
    }

    return array;
  }
}