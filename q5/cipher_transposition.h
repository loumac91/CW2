typedef struct node {
  int original_index;
  int index;
  struct node* next;
} index_shift_node;

// API Functions

index_shift_node* get_head_index_shift_node(char cipher_key[], const int cipher_length);

void encrypt_file();