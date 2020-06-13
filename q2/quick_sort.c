#include <stdio.h>

void swap(int *array_pointer, int swapIndex, int swapValue, int originIndex) {
  int temp = array_pointer[swapIndex];
  array_pointer[swapIndex] = swapValue;
  array_pointer[originIndex] = temp;
}

// *array_pointer is equivalent to array[], can't pass array as value in C.
// Length therefore must be provided as separate argument (unless it is specified explicitly e.g. array[5])
void quick_sort(int *array_pointer, int length) {
  // 1. Base case for recursion - return if nothing left to sort
  if (length <= 1) return;

  // 1. Set pivot to be 
  int pivot = array_pointer[length - 1]; // This implementation follows example provided in slides

  int swapIndex = 0, i = 0;
  while (i < length - 1) {
    int a = array_pointer[i];

    if (a <= pivot) {
      swap(array_pointer, swapIndex, a, i);
      swapIndex++;
    }

    i++;
  }
  
  swap(array_pointer, swapIndex, pivot, length - 1);

  quick_sort(array_pointer, swapIndex); // Left partition
  quick_sort(array_pointer + swapIndex, length - swapIndex); // Right partition
}