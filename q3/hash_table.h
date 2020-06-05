// can use struct instead?

typedef struct {
  char* key;
  char* value;
} key_value_pair;

typedef struct {
  int size;
  int count;
  key_value_pair** key_value_pairs;
} hash_table;

// Definition for what a deleted key value pair will look like
static key_value_pair DELETED_KEY_VALUE_PAIR = { NULL, NULL };

// Instance functions

hash_table* create_hash_table(const int table_size);

void delete_hash_table(hash_table* ht);

// API Functions

void insert(hash_table* ht, const char* key, const char* value);

void remove_key(hash_table* ht, const char* key); // This was renamed as delete and remove are reserved

char* search(hash_table* ht, const char* key);
