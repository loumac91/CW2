#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "quick_sort.h"

void print_array(char *print_statement, int *array_pointer, int length) 
{
  printf("%s \n", print_statement);

  for (int i = 0; i < length; i++)
  {    
    printf("%d%s", array_pointer[i], (i == length - 1 ? "\n" : ", "));
  }

  printf("\n");
}

void sort_and_print_array(int *array_pointer, int length)
{
  print_array("Before sorting:", array_pointer, length);

  quick_sort(array_pointer, length);

  print_array("After sorting:", array_pointer, length);
}

void sort_example_from_specification() 
{
  int example[] = { 3, -2, 1 };

  int length = sizeof example / sizeof example[0];

  sort_and_print_array(example, length);
}

void sort_set_of_ten() 
{
  int larger_set[] = { 10, 7, 9, 4, 1, 3, 2, 5, 6, 8 };

  int length = sizeof larger_set / sizeof larger_set[0];

  sort_and_print_array(larger_set, length);
}

void sort_random_set_of_positive_and_negative_integers() 
{
  // 1. Create 100 element array
  int size = 100;
  int *array = (int*)malloc(size * sizeof(int));

  // 2. Fill it with positive and negative numbers (between -100 and 100)
  for (int i = 0; i < size; i++)
  {
    int is_positive = rand() % 2;
    array[i] = (rand() % 100 + 1) * (is_positive == 1 ? 1 : -1);
  }

  // 3. Sort and Print
  sort_and_print_array(array, size);
}

// Q2. Implement a quick sort algorithm for a list of numbers (positive and negative) in C.
// e.g. [3, -2, 1] becomes [-2, 1, 3]

int main() 
{
  srand(time(0)); // Sets current time as seed for rand()

  sort_example_from_specification();

  // Examples with larger sets of data
  sort_set_of_ten();
  sort_random_set_of_positive_and_negative_integers();

  return 0;
}
