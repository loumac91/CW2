#include <stdio.h>

void swap(int *array_pointer, int swapIndex, int swapValue, int originIndex)
{
  int temp = array_pointer[swapIndex];
  array_pointer[swapIndex] = swapValue;
  array_pointer[originIndex] = temp;
}

// *array_pointer is equivalent to array[], can't pass array as value in C.
// Length therefore must be provided as separate argument (unless it is specified explicitly e.g. array[5])
void quick_sort(int *array_pointer, int length)
{
  // 1. Base case for recursion - return if nothing left to sort
  if (length <= 1) return;
 
  // 2. Select the median value as the pivot (although this could be any index)
  // int pivot = array_pointer[length / 2];
 
  // 3. Prepare the left and right partitions to recurse over (partition is a subset array of the *array_pointer array)
  // int left = 0, right = length - 1;
  // while (1) // Equivalent to while (true)
  // {    
  //   while (array_pointer[left] < pivot) left++; // Find an index value from the left that is >= pivot (median value)
  //   while (array_pointer[right] > pivot) right--; // Repeat again but with the value <= pivot
 
  //   if (left >= right) break; // If left and right indexes match or cross over, exit loop
 
  //   // Swap/sort values
  //   int swap_value = array_pointer[left];
  //   array_pointer[left++] = array_pointer[right];
  //   array_pointer[right--] = swap_value;
  // }

  // 4. Recurse sorting on left and right partitions
  // quick_sort(array_pointer, left); // Left partition
  // quick_sort(array_pointer + left, length - left); // Right partition

  // 1. Set pivot to be 
  int pivot = array_pointer[length / 2];

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