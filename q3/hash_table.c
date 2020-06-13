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

static key_value_pair* create_key_value_pair(const char* key, const char* value) {
  // Use of malloc here allows for use of free when we want to delete pointers
  key_value_pair* kvp = malloc(sizeof(key_value_pair));  

  // Set key and value pointers by duplicating key and value pointers
  kvp -> key = strdup(key); // strdup does malloc + strcpy under the hood
  kvp -> value = strdup(value);
  
  return kvp;
}

static void delete_key_value_pair(key_value_pair* kvp) {
  free(kvp -> key);
  free(kvp -> value);
  free(kvp);
}

static int get_load_factor(hash_table* ht) {
  return (ht -> count * 100) / ht -> size;
}

static void resize_hash_table(hash_table* ht, const double size_change) {
  int new_size = (int)((ht -> size) * size_change);
  if (new_size <= 2) {
    return;
  }

  // Copy across all existing values into new temporary table
  hash_table* temp_hash_table = create_hash_table(new_size);
  for (int i = 0; i < ht -> size; i++) {
    key_value_pair* kvp = ht -> key_value_pairs[i];
    if (kvp != NULL && kvp != &DELETED_KEY_VALUE_PAIR) {
      // This reindexes the key value pairs
      insert(temp_hash_table, kvp -> key, kvp -> value);
    }
  }

  // Update the count of the hash table
  ht -> count = temp_hash_table -> count;

  // Swap in the new size
  int temp_size = ht -> size;
  ht -> size = temp_hash_table -> size;
  temp_hash_table -> size = temp_size;

  // Swap in the new items
  key_value_pair** temp_key_value_pairs = ht -> key_value_pairs;
  ht -> key_value_pairs = temp_hash_table -> key_value_pairs;
  temp_hash_table -> key_value_pairs = temp_key_value_pairs;

  // Delete temporary table
  delete_hash_table(temp_hash_table);
}

// INSTANCE FUNCTIONS

hash_table* create_hash_table(const int table_size) {
  hash_table* ht = malloc(sizeof(hash_table));

  ht -> size = table_size;
  ht -> count = 0;
  // Set kvp array to be (table_size) elements long with each element being key_value_pair* bytes in size
  // the cast to size_t in order to mimic call of sizeof function (which has a return type of size_t)
  ht -> key_value_pairs = calloc((size_t)ht -> size, sizeof(key_value_pair*));

  return ht;
}

void delete_hash_table(hash_table* ht) {
  for (int i = 0; i < ht -> size; i++) {
    key_value_pair* kvp = ht -> key_value_pairs[i];
    if (kvp != NULL && kvp != &DELETED_KEY_VALUE_PAIR) { // & is the Address operator
      delete_key_value_pair(kvp);
    }
  }

  free(ht -> key_value_pairs);
  free(ht);
}

// API METHODS - These all use open addressing, by probing indexes
// For example, insert probes until it can find either an empty index
// or an index with the same key to insert/update

void insert(hash_table* ht, const char* key, const char* value) {
  if (get_load_factor(ht) >= 80) {
    resize_hash_table(ht, SIZE_INCREASE);
  }

  key_value_pair* kvp = create_key_value_pair(key, value);

  int index = get_hash_index(kvp -> key, ht -> size, 0);
  
  key_value_pair* current_kvp = ht -> key_value_pairs[index];

  // Handle collisions
  int attemtpts = 1;
  while (current_kvp != NULL && current_kvp != &DELETED_KEY_VALUE_PAIR) {
    // If it exists, insert over the top
    if (string_compare(current_kvp -> key, key) == 0) {
      delete_key_value_pair(current_kvp);
      ht -> key_value_pairs[index] = kvp;
      return;
    }

    index = get_hash_index(kvp -> key, ht -> size, attemtpts++);
    current_kvp = ht -> key_value_pairs[index];
  }

  // Add it to free index if not previously existing
  ht -> key_value_pairs[index] = kvp;
  ht -> count++;
}

void remove_key(hash_table* ht, const char* key) {
  if (get_load_factor(ht) <= 20) {
    resize_hash_table(ht, SIZE_DECREASE);
  }

  int index = get_hash_index(key, ht -> size, 0);
  key_value_pair* current_kvp = ht -> key_value_pairs[index];

  int attempts = 1;
  while (current_kvp != NULL && current_kvp != &DELETED_KEY_VALUE_PAIR) {
    // If key found, delete key value pair, replace index with null pair and 
    // decrease hash table count
    if (string_compare(current_kvp -> key, key) == 0) {
      delete_key_value_pair(current_kvp);
      ht -> key_value_pairs[index] = &DELETED_KEY_VALUE_PAIR;
      ht -> count--; 
      return;
    }

    index = get_hash_index(key, ht -> size, attempts++);
    current_kvp = ht -> key_value_pairs[index];
  }
}

char* search(hash_table* ht, const char* key) {
  int index = get_hash_index(key, ht -> size, 0);
  key_value_pair* kvp = ht -> key_value_pairs[index];

  int attempts = 1;
  while (kvp != NULL && kvp != &DELETED_KEY_VALUE_PAIR) {
    // If key found, return it's corresponding value
    if (string_compare(kvp -> key, key) == 0) {
      return kvp -> value;
    }

    index = get_hash_index(key, ht -> size, attempts++);
    kvp = ht -> key_value_pairs[index];
  }

  return NULL;
}
