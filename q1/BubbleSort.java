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

  // Outer do while loop attempts to iterate through each character from index 0 => last index
  // Each iteration compares every value in the String array left to right - to determine whether it should "bubble" up to next index
  // As i increments, it is used to "pad" the right hand side of the array as we no longer need to evaluate those elements as they are in their sorted state
  // This is so say that each time i increments - and swapped = true - then a value reached its final sorted index
  // If sorted = false, no swaps occured and the array is in its sorted state
  // This optimisation helps prevent unnecessary iteration

  private static String[] sort(String[] array, SwapBase swapper) {
    // Guard against redundant and dangerous input
    if (array.length <= 1) return array;

    Boolean swapped = false;
    int i = 0;
    do {
      int j = 0;
      swapped = false; // Reset swapped variable after previous iteration has completed a sort pass
      while (j < array.length - 1 - i) {
        String a = array[j];
        String b = array[j + 1];
  
        if (swapper.shouldSwap(a, b)) {
          swapper.swap(array, j, a, b);
          swapped = true;
        }
  
        j++;
      }

      i++;
    } while (swapped && i < array.length - 1); // Stop if there was no swap or finished iterating entire array

    return array;
  }
}