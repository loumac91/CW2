#include <stdio.h>

#include "hash_table.h"

// DELETE
// https://github.com/jamesroutley/algorithms-and-data-structures/pull/1/files
// http://www.cse.yorku.ca/~oz/hash.html
// https://github.com/jamesroutley/write-a-hash-table/tree/master/04-collisions


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

