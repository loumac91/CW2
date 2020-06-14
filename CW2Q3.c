#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "q3/hash_table.h"

// Q3. Implement your own Hash Table in C for storing and searching for names i.e. strings or char arrays.
// Your Hash Table implementation should have the following interface written below in pseudocode.
// Note: how you define the structures, functions, hashing algorithm and the limit (if any) of the number
// of items that can be stored in the Hash Table etc. is up to you.

// I've interpreted Hash Table here as similar to what a Java hash set would be - i.e. that it only stores the key
// Hash Table in Java usually stores key value pairs - isn't implemented here as we only need to store names

int main() {
  // 1. Create hash table with an initial size of 10
  hash_table* hash_table = create_hash_table(10);

  // 2. Insert 8 keys
  // (Note the first two will have the same hash) as the hashing function
  // sums the ASCI characters. So a collision is guaranteed here
  printf("Inserting 8 keys into hashtable...\n");

  insert(hash_table, "Matt");
  printf("matt done");
  insert(hash_table, "ttaM");
  insert(hash_table, "Jacob");
  insert(hash_table, "Elizabeth");
  insert(hash_table, "John");
  insert(hash_table, "Becky");
  insert(hash_table, "Robin");
  insert(hash_table, "Sam");

  printf("*Before size increase*\n");
  printf("Number of items in hash table: %d\n", hash_table -> count);
  printf("Size of hash table %d\n", hash_table -> size);
  printf("\n");

  // 3. Table resizes on this next call as the load factor has reached 80%
  // Any attempt to insert without the resize in place beyond this point
  // would exponentially increase number of collisions and slow down insertion
  // dramatically
  insert(hash_table, "Steve");

  printf("*After size increase*\n");
  printf("Number of items in hash table: %d\n", hash_table -> count);
  printf("Size of hash table %d\n", hash_table -> size);
  printf("\n");

  // 4. Search for values
  int result_one = search(hash_table, "Elizabeth");
  printf("When searching for key 'Elizabeth' value returned is: '%d'\n", result_one);
  int result_two = search(hash_table, "Matt");
  printf("When searching for key 'Matt' value returned is: '%d'\n", result_two);
  printf("\n");

  // 5. Remove items and see resize
  printf("*Before size decrease*\n");
  printf("Number of items in hash table: %d\n", hash_table -> count);
  printf("Size of hash table %d\n", hash_table -> size);
  printf("\n");
  
  remove_key(hash_table, "Matt");
  remove_key(hash_table, "ttaM");
  remove_key(hash_table, "Jacob");
  remove_key(hash_table, "Elizabeth");
  remove_key(hash_table, "John");
  remove_key(hash_table, "Becky");
  remove_key(hash_table, "Sam");

  printf("*After size decrease*\n");
  printf("Number of items in hash table: %d\n", hash_table -> count);
  printf("Size of hash table %d\n", hash_table -> size);
  printf("\n");

  // 7. Repeat search on deleted key to see no result
  int result_three = search(hash_table, "Matt");
  printf("When searching for removed key 'Hello', '0' (or false) should be returned. Returned value is: '%d'\n", result_three);
  
  // 6. Delete table
  delete_hash_table(hash_table);
}
