package q1;

import q1.swap.SwapBase;
import q1.swap.SwapAscending;
import q1.swap.SwapDescending;

public class BubbleSort {
  
  public static <T> T[] sort(T[] stringArray) {
    return sort(stringArray, new SwapAscending());
  }

  public static <T> T[] sort(T[] stringArray, SortOrder sortOrder) {
    switch (sortOrder) {
      case DESCENDING: 
        return sort(stringArray, new SwapDescending());
      default:
        return sort(stringArray, new SwapAscending());
    }
  }

  // Outer loop, goes through each index i.e. 0 => last index
  // Each loop of the outer loop guarantees atleast one value gets to it's sorted po
  // Hence why the inner loop's end condition is length - 1 - i
  // i being the amount of indexes to ignore from the end of the array
  // This increases each iteration
  // Inner loop

  // each iteration of the outer loop, guarantees that one value will reach its sorted state at the end of the array
  // this is because a comparison pass is made for that value agaisnt every other value int he array 
  private static <T> T[] sort(T[] array, SwapBase swapper) {
    for (int i = 0; i < array.length - 1; i++) { // < rather than <= because we will access + 1
      for (int j = 0; j < array.length - 1 - i; j++) {
        T a = array[j];
        T b = array[j + 1];
        if (swapper.shouldSwap(a, b)) {
          swapper.swap(array, j, a, b);
        }

        for (T string : array) {
          System.out.print(string.toString() + ", ");
        }
        System.out.println();
      }
    }

    return array;
  }
}