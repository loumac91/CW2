package q1;

import q1.swap.SwapBase;
import q1.swap.SwapAscending;
import q1.swap.SwapDescending;

public class BubbleSort {
  
  public static String[] sort(String[] stringArray) {
    return sort(stringArray, new SwapAscending<String>());
  }

  public static String[] sort(String[] stringArray, SortOrder sortOrder) {
    switch (sortOrder) {
      case DESCENDING: 
        return sort(stringArray, new SwapDescending<String>());
      default:
        return sort(stringArray, new SwapAscending<String>());
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
  private static String[] sort(String[] stringArray, SwapBase<String> swapper) {
    for (int i = 0; i < stringArray.length - 1; i++) { // < rather than <= because we will access + 1
      for (int j = 0; j < stringArray.length - 1 - i; j++) {
        String a = stringArray[j];
        String b = stringArray[j + 1];
        if (swapper.shouldSwap(a, b)) {
          swapper.swap(stringArray, j, a, b);
        }

        for (String string : stringArray) {
          System.out.print(string + ", ");
        }
        System.out.println();
      }
    }

    return stringArray;
  }
}