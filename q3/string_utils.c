static int get_min_length(const int a, const int b) {
  return a < b ? a : b;
}

// Alternative to strlen
int get_string_length(const char* s) {
  int i = 0;
  while (*(s++)) i++; // This iterates up until '\0' (NULL character) is found
  return i;
}

// Alternative to strcmp
int string_compare(const char* a, const char* b) {
  int a_length = get_string_length(a);
  int b_length = get_string_length(b);
  int last_shared_index = get_min_length(a_length, b_length) - 1;

  int i = 0;
  while (i <= last_shared_index) {
    int a_char = a[i];
    int b_char = b[i++];
    if (a_char != b_char) return a_char - b_char;
  }

  return a_length - b_length;
}