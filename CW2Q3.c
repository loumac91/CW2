#include <stdio.h>

#include "q3/hash_table.h"

// Q3. Implement your own Hash Table in C for storing and searching for names i.e. strings or char arrays.
// Your Hash Table implementation should have the following interface written below in pseudocode.
// Note: how you define the structures, functions, hashing algorithm and the limit (if any) of the number
// of items that can be stored in the Hash Table etc. is up to you.

int main() {
  // 1. Create hash table with an initial size of 10
  hash_table* hash_table = create_hash_table(10);

  // 2. Insert 8 pairs
  // (Note the first two will have the same hash) as the hashing function
  // sums the ASCI characters. So a collision is guaranteed here
  printf("Inserting 8 items into hashtable...\n");

  insert(hash_table, "Hello", "World");
  insert(hash_table, "olleH", "World");
  insert(hash_table, "City", "Bristol");
  insert(hash_table, "Street", "Gloucester Road");
  insert(hash_table, "Postcode", "BR1 5TL");
  insert(hash_table, "Phone Number", "07123456789");
  insert(hash_table, "House Number", "231");
  insert(hash_table, "Email", "hacker@anonymous.net");

  printf("*Before size increase*\n");
  printf("Number of key value pairs in hash table: %d\n", hash_table -> count);
  printf("Size of hash table %d\n", hash_table -> size);
  printf("\n");

  // 3. Table resizes on this next call as the load factor has reached 80%
  // Any attempt to insert without the resize in place beyond this point
  // would exponentially increase number of collisions and slow down insertion
  // dramatically
  insert(hash_table, "After resize", "value");

  printf("*After size increase*\n");
  printf("Number of key value pairs in hash table: %d\n", hash_table -> count);
  printf("Size of hash table %d\n", hash_table -> size);
  printf("\n");

  // 4. Search for values
  char* result_one = search(hash_table, "Email");
  printf("When searching for key 'Email' value returned is: '%s'\n", result_one);
  char* result_two = search(hash_table, "Hello");
  printf("When searching for key 'Hello' value returned is: '%s'\n", result_two);
  printf("\n");

  // 5. Remove items and see resize
  printf("*Before size decrease*\n");
  printf("Number of key value pairs in hash table: %d\n", hash_table -> count);
  printf("Size of hash table %d\n", hash_table -> size);
  printf("\n");

  remove_key(hash_table, "Hello");
  remove_key(hash_table, "City");
  remove_key(hash_table, "Street");
  remove_key(hash_table, "Postcode");
  remove_key(hash_table, "Phone Number");
  remove_key(hash_table, "House Number");
  remove_key(hash_table, "Email");

  printf("*After size decrease*\n");
  printf("Number of key value pairs in hash table: %d\n", hash_table -> count);
  printf("Size of hash table %d\n", hash_table -> size);
  printf("\n");

  // 7. Repeat search on deleted key to see no result
  char* result_three = search(hash_table, "Hello");
  printf("When searching for removed key 'Hello', 'null' should be returned. Returned value is: '%s'\n", result_three);
  

  // 6. Delete table
  delete_hash_table(hash_table);
}
