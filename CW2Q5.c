#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stddef.h>

#include "q5/cipher_transposition.h"

// Alternative to strlen
static int get_string_length(char* s) {
  int i = 0;
  while (*(s++)) i++; // This iterates up until '\0' (NULL character) is found
  return i;
}

// Q5. Implement a Columnar Transposition Cipher in C to encrpyt a message
// of any length. A Columnar Transposition Cipher is transposition cipher that
// follows a simple rule for mixing up the characters in the plaintext to form 
// the ciphertext

// Approach is to precalculate index shifts to use as follows:
// e.g. for LOVELACE, when this key is sorted the following indexes are swapped
// | L | O | V | E | L | A | C | E |
// | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
// becomes
// | A | C | E | E | L | L | O | V |
// | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | new_index
// | 5 | 6 | 3 | 7 | 0 | 4 | 1 | 2 | original_index

// Therefore, using the first 8 characters of the input file as an example:
// i.e. "It will "
// | I | t |   | w | i | l | l |   |
// becomes
// | l | l | w |   | I | i | t |   |

// Once the shiftings are known and stored
// Read n (length of key) characters a time from input file
// And shift from original index to new (sorted) index
// And write to file

int main() {
  // char cipher_key[] = "KEYS";
  // const char* input_filepath = "./q5/small.txt";

  char cipher_key[] = "LOVELACE";
  const char* input_filepath = "./q5/text.txt";
  const char* output_filepath = "./q5/output.txt";

  printf("Using key: '%s' ", cipher_key);
  printf("to encrypt text file '%s'\n", input_filepath);

  // Open file
  FILE *text_input_file = fopen(input_filepath, "r");
  FILE *cipher_output_file = fopen(output_filepath, "w");

  if (text_input_file == NULL) {
    perror("Could not find file");
    exit(EXIT_FAILURE);
  } 

  // Get index shifts
  const int cipher_length = get_string_length(cipher_key);
  index_shift_node *head = get_head_index_shift_node(cipher_key, cipher_length);  
  
  index_shift_node* current = head;
  int needs_padding = 0, skip = 0;
  const int read_length = cipher_length + 1; // + 1 to shift '\0'

  char *buffer, *encrypted_chars;
  buffer = malloc(sizeof(char) * read_length);
  encrypted_chars = malloc(sizeof(char) * read_length);  

  while (fgets(buffer, read_length, text_input_file)) {
    current = head;
    while (current != NULL) {      
      char character = buffer[current -> original_index];
      if (character == '\0') needs_padding = 1;
      if (character == '\n') {
        fprintf(cipher_output_file, "%c", character);
        skip = 1;
        break;
      }

      encrypted_chars[current -> index] = needs_padding == 1 ? 'X' : character;
      current = current -> next;
    }

    if (skip == 1) {
      skip = 0;
      memset(encrypted_chars, 0, read_length * (sizeof encrypted_chars[0])); // skipping line, so reset sorted buffer
    } else {
      for (int i = 0; i < cipher_length; i++) {
        fprintf(cipher_output_file, "%c", encrypted_chars[i]);
      }
    }

    needs_padding = 0;
  }

  printf("Encryption complete, output file location: '%s'", output_filepath);

  int close_file = fclose(text_input_file);
  int close_output_file = fclose(cipher_output_file);

  return 0;
}