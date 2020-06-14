// Magic numbers
#define PRIME 787
#define SIZE_INCREASE 2.5
#define SIZE_DECREASE 0.5
#define SIZE_INCREASE_THRESHOLD 80
#define SIZE_DECREASE_THRESHOLD 20

typedef struct {
  char* key;
} hash_table_item;

typedef struct {
  int size;
  int count;
  hash_table_item** hash_table_items;
} hash_table;

// Definition for what a deleted key will look like
static hash_table_item DELETE_HASH_TABLE_ITEM = { NULL };

// Instance functions

hash_table* create_hash_table(const int table_size);

void delete_hash_table(hash_table* hash_table);

// API Functions - key is synonymous with name here

void insert(hash_table* hash_table, const char* key);

void remove_key(hash_table* hash_table, const char* key); // This was renamed as delete and remove are reserved

int search(hash_table* hash_table, const char* key); // Treating int like a bit (i.e. 0 (false) or 1 (true)
