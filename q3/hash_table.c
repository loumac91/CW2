#include <stdlib.h>
#include <string.h>

#include "hash_table.h"
#include "hashing.h"
#include "string_utils.h"

// STATIC FUNCTIONS
// These are internal to this file (can only be called from within file), hence static 

// Double hashing method of applying two hashes together
// Collisions are mitigated by adding the attempt number onto the first hash
static int get_hash_index(const char* key, const int hash_table_size, const int attempt) {
  int hashed_key = get_hashed_key(key);
  int hash_one = hashed_key % hash_table_size;
  int hash_two = PRIME - (hashed_key % PRIME);
  int index = ((hash_one + attempt) * hash_two) % hash_table_size;
  return index;
}

static hash_table_item* create_hash_table_item(const char* key) {
  // Use of malloc here allows for use of free when we want to delete pointers
  hash_table_item* hash_table_item = malloc(sizeof(hash_table_item));  

  // Set key and value pointers by duplicating key and value pointers
  hash_table_item -> key = strdup(key); // strdup does malloc + strcpy under the hood
  
  return hash_table_item;
}

static void delete_hash_table_item(hash_table_item* hash_table_item) {
  free(hash_table_item -> key);
  free(hash_table_item);
}

static int get_load_factor(hash_table* ht) {
  return (ht -> count * 100) / ht -> size;
}

static void resize_hash_table(hash_table* ht, const double size_change) {
  int new_size = (int)((ht -> size) * size_change);
  if (new_size <= 2) {
    return;
  }

  // Copy across all existing keys into new temporary table
  hash_table* temp_hash_table = create_hash_table(new_size);
  for (int i = 0; i < ht -> size; i++) {
    hash_table_item* hash_table_item = ht -> hash_table_items[i];
    if (hash_table_item != NULL && hash_table_item != &DELETE_HASH_TABLE_ITEM) {
      // This reindexes the keys in the table
      insert(temp_hash_table, hash_table_item -> key);
    }
  }

  // Update the count of the hash table
  ht -> count = temp_hash_table -> count;

  // Swap in the new size
  int temp_size = ht -> size;
  ht -> size = temp_hash_table -> size;
  temp_hash_table -> size = temp_size;

  // Swap in the new keys
  hash_table_item** temp_hash_table_items = ht -> hash_table_items;
  ht -> hash_table_items = temp_hash_table -> hash_table_items;
  temp_hash_table -> hash_table_items = temp_hash_table_items;

  // Delete temporary table
  delete_hash_table(temp_hash_table);
}

// INSTANCE FUNCTIONS

hash_table* create_hash_table(const int table_size) {
  hash_table* ht = malloc(sizeof(hash_table));

  ht -> size = table_size;
  ht -> count = 0;
  // Set hash_items "array" to be (table_size) elements long with each element being hash_table_item* bytes in size
  // the cast to size_t in order to mimic call of sizeof function (which has a return type of size_t)
  ht -> hash_table_items = calloc((size_t)ht -> size, sizeof(hash_table_item*));

  return ht;
}

void delete_hash_table(hash_table* ht) {
  for (int i = 0; i < ht -> size; i++) {
    hash_table_item* hash_table_item = ht -> hash_table_items[i];
    if (hash_table_item != NULL && hash_table_item != &DELETE_HASH_TABLE_ITEM) { // & is the Address operator
      delete_hash_table_item(hash_table_item);
    }
  }

  free(ht -> hash_table_items);
  free(ht);
}

// API METHODS - These all use open addressing, by probing indexes
// For example, insert probes until it can find either an empty index
// or an index with the same key to insert/update

void insert(hash_table* ht, const char* key) {
  if (get_load_factor(ht) >= SIZE_INCREASE_THRESHOLD) {
    resize_hash_table(ht, SIZE_INCREASE);
  }

  hash_table_item* ht_item = create_hash_table_item(key); // Have to abbreviate to avoid conflicts

  int index = get_hash_index(ht_item -> key, ht -> size, 0);
  
  hash_table_item* current_hash_table_item = ht -> hash_table_items[index];

  // Probing strategy to handle collisions
  int attemtpts = 1;

  while (current_hash_table_item != NULL && current_hash_table_item != &DELETE_HASH_TABLE_ITEM) {
    // If it exists, insert over the top
    if (string_compare(current_hash_table_item -> key, key) == 0) {
      delete_hash_table_item(current_hash_table_item);
      ht -> hash_table_items[index] = ht_item;
      return;
    }

    index = get_hash_index(ht_item -> key, ht -> size, attemtpts++);
    current_hash_table_item = ht -> hash_table_items[index];
  }

  // Add it to free index if not previously existing
  ht -> hash_table_items[index] = ht_item;
  ht -> count++;
}

void remove_key(hash_table* ht, const char* key) {
  if (get_load_factor(ht) <= SIZE_DECREASE_THRESHOLD) {
    resize_hash_table(ht, SIZE_DECREASE);
  }

  int index = get_hash_index(key, ht -> size, 0);
  hash_table_item* current_hash_table_item = ht -> hash_table_items[index];

  int attempts = 1;
  while (current_hash_table_item != NULL && current_hash_table_item != &DELETE_HASH_TABLE_ITEM) {
    // If key found, delete key, replace index with null key items and 
    // decrease hash table count
    if (string_compare(current_hash_table_item -> key, key) == 0) {
      delete_hash_table_item(current_hash_table_item);
      ht -> hash_table_items[index] = &DELETE_HASH_TABLE_ITEM;
      ht -> count--; 
      return;
    }

    index = get_hash_index(key, ht -> size, attempts++);
    current_hash_table_item = ht -> hash_table_items[index];
  }
}

int search(hash_table* ht, const char* key) {
  int index = get_hash_index(key, ht -> size, 0);
  hash_table_item* hash_table_item = ht -> hash_table_items[index];

  int attempts = 1;
  while (hash_table_item != NULL && hash_table_item != &DELETE_HASH_TABLE_ITEM) {
    // If key found, return 1 (equivalent to boolean)
    if (string_compare(hash_table_item -> key, key) == 0) {
      return 1;
    }

    index = get_hash_index(key, ht -> size, attempts++);
    hash_table_item = ht -> hash_table_items[index];
  }

  return 0;
}
