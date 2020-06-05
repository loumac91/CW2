#include <stdlib.h>

// TODO

static int HASH_PRIME = 5831;

// THERE IS AN EXAMPLE IN MOODLE to follow

int get_hash_key(const char* key) {
   int hash = HASH_PRIME;
   const int length = strlen(key);

   for(int i = 0; i < length; key++, i++) // key++ progress through chars in array
   {   
      hash = ((hash << 5) + hash) + (*key);
   }

  return (int)hash;
}