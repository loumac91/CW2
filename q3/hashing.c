// Adds up the ASCI characters
int get_hashed_key(const char* key) {
  int hash = 0;
  for (int i = 0; key[i] != '\0'; i++) {
    hash = hash + key[i];
  }

  return hash;
}
