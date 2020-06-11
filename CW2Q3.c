#include <stdio.h>

#include "q3/hash_table.h"

// DELETE
// https://github.com/jamesroutley/algorithms-and-data-structures/pull/1/files
// http://www.cse.yorku.ca/~oz/hash.html
// https://github.com/jamesroutley/write-a-hash-table/tree/master/04-collisions

// Q3. Implement your own Hash Table in C for storing and searching for names i.e. strings or char arrays.
// Your Hash Table implementation should have the following interface written below in pseudocode.
// Note: how you define the structures, functions, hashing algorithm and the limit (if any) of the number
// of items that can be stored in the Hash Table etc. is up to you.

int main() 
{
  hash_table* hash_table = create_hash_table(1000);
  insert(hash_table, "wakanda", "forever");
  insert(hash_table, "hello", "world");

  char* c = search(hash_table, "wakanda");
  printf("before %s", c);

  // Probably don't need this - BUT COULD INCLUDE ANYWAY
  delete_hash_table(hash_table);
  printf("after %s", c);
}
