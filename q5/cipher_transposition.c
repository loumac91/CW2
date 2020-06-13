#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>

#include "cipher_transposition.h"

static void push_to_head(index_shift_node **head, const int original_index) {
  index_shift_node* new_head = (index_shift_node*)malloc(sizeof(index_shift_node));
  new_head -> original_index = original_index;
  new_head -> index = original_index;
  new_head -> next = *head;

  *head = new_head;
}

static void update_item(index_shift_node **head, const int current_index, const int new_index) {
  index_shift_node* current = *head;
  while (current != NULL) {
    if (current -> index == current_index) {
      current -> index = new_index;
      return;
    }

    current = current -> next;
  }
}

index_shift_node* get_head_index_shift_node(char cipher_key[], const int cipher_length) {
  index_shift_node *head = NULL;
  for (int i = 0; i < cipher_length; i++) {
    push_to_head(&head, i);
  }

  int swapped_flag = 0;
  int i = 0;
  do {
    int j = 0;
    swapped_flag = 0;

    while (j < cipher_length - 1 - i) {
      if (cipher_key[j] > cipher_key[j + 1]) {
        int t = cipher_key[j];
        cipher_key[j] = cipher_key[j + 1];
        cipher_key[j + 1] = t;
        swapped_flag = 1; 
            
        update_item(&head, j, j + 1);      
        update_item(&head, j + 1, j);
      }
      
      j++;
    }

    i++;
  } while (swapped_flag = 1 && i < cipher_length - 1);

  return head;
}
