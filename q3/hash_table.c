#include <stdlib.h>
#include <string.h>

#include "hash_table.h"
#include "hashing.h";

// STATIC FUNCTIONS
// These are internal to this file

static int get_hash_index(const char* key, const int hash_table_size, const int attempt) {
  int result = get_hashed_key(key) % hash_table_size;
  
  return result;
}

static key_value_pair* create_key_value_pair(const char* key, const char* value) {
  // Allocate memory for a key value pair pointer 
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

// INSTANCE FUNCTIONS

hash_table* create_hash_table(const int table_size) {
  // Allocate memory for hash table pointer
  hash_table* ht = malloc(sizeof(hash_table));

  ht -> size = table_size;
  ht -> count = 0;
  // Set kvp array to be (table_size) elements long with each element being key_value_pair* bytes in size
  // the case to size_t in order to mimic call of sizeof function (which has a return type of size_t)
  ht -> key_value_pairs = calloc((size_t)ht -> size, sizeof(key_value_pair*));

  return ht;
}

void delete_hash_table(hash_table* ht) {
  for (int i = 0; i < ht -> size; i++) {
    key_value_pair* kvp = ht -> key_value_pairs[i];
    if (kvp != NULL && kvp != &DELETED_KEY_VALUE_PAIR) {
      delete_key_value_pair(kvp);
    }
  }

  free(ht -> key_value_pairs);
  free(ht);
}

// API METHODS

void insert(hash_table* ht, const char* key, const char* value) {

  key_value_pair* kvp = create_key_value_pair(key, value);

  int index = get_hash_index(key, ht -> size, 0);
  
  // TODO - Handle collisions


  ht -> key_value_pairs[index] = kvp;
  ht -> count++;
}

void remove_key(hash_table* ht, const char* key) {

  int index = get_hash_index(key, ht -> size, 0);
  key_value_pair* kvp = ht -> key_value_pairs[index];

  int attempts = 1;
  while (kvp != NULL && kvp != &DELETED_KEY_VALUE_PAIR)
  {
    if (strcmp(kvp -> key, key) == 0) {
      delete_key_value_pair(kvp);
      ht -> key_value_pairs[index] = &DELETED_KEY_VALUE_PAIR;
      ht -> count--;
      return;
    }

    index = get_hash_index(key, ht -> size, attempts++);
    kvp = ht -> key_value_pairs[index];
  }
}

char* search(hash_table* ht, const char* key) {
  int index = get_hash_index(key, ht -> size, 0);
  key_value_pair* kvp = ht -> key_value_pairs[index];

  int attempts = 1;
  while (kvp != NULL && kvp != &DELETED_KEY_VALUE_PAIR) {
    // DO OWN STRING COMPARISON
    if (strcmp(kvp -> key, key) == 0) {
      return kvp -> value;
    }

    index = get_hash_index(key, ht -> size, attempts++);
    kvp = ht -> key_value_pairs[index];
  }

  return NULL;
}
