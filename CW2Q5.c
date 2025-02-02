#include <stdio.h>
#include <stdlib.h>

#include "q5/cipher_transposition.h"

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

// In the following code, whitespace and new lines have been preserved in the output file.
// This was done so that it should be possible to decrypt the file back to it's original state
// by simply shifting the indexes of the file back into their correct position

int main(int argc, char const *argv[]) {
  char cipher_key[] = "LOVELACE";
  const char* input_filepath = "./q5/text.txt";
  const char* output_filepath = "./q5/output.txt";

  printf("Using key: '%s'\n", cipher_key);
  printf("To encrypt text file '%s'\n", input_filepath);

  // 1. Open input file
  FILE *text_input_file = fopen(input_filepath, "r");

  if (text_input_file == NULL) {
    perror("Could not find file");
    exit(EXIT_FAILURE);
  }

  // 2. Read its entire contents to a string;
  fseek(text_input_file, 0, SEEK_END);
  long file_size = ftell(text_input_file);
  fseek(text_input_file, 0, SEEK_SET); 
  char *string = malloc(file_size + 1);
  fread(string, 1, file_size, text_input_file);

  fclose(text_input_file);

  string[file_size] = '\0'; // Terminate string

  // 3. Get shift indexes
  // The main idea with this is to only calculate shifting once and apply it 
  // repeatedly after, as opposed to repeatedly calulcating and shifting
  int cipher_length = 8;
  index_shift_node *head = get_head_index_shift_node(cipher_key, cipher_length);

  // 4. Allocate memory buffers
  const int buffer_length = cipher_length + 1; // + 1 to accommodate '\0'
  char *buffer, *encrypted_chars;
  buffer = calloc(buffer_length, sizeof(char));
  encrypted_chars = calloc(buffer_length, sizeof(char)); 

  // 5. Open output file
  FILE *cipher_output_file = fopen(output_filepath, "w");

  // 6. Iterate through each character, placing n (length of cipher) characters
  // into buffer to be shifted for encryption
  index_shift_node* current = head;
  int character_count = 0, i = 0;
  while (i < file_size - 1) {
    // Every n characters - shift all characters to encryption indexes
    if (character_count == cipher_length) { 
      current = head;
      while (current != NULL) {      
        // This essentially swaps it's original index with it's encrypted index
        char character = buffer[current -> original_index];
        encrypted_chars[current -> index] = character;
        current = current -> next;
      }

      for (int char_index = 0; char_index < cipher_length; char_index++) {
        fprintf(cipher_output_file, "%c", encrypted_chars[char_index]);
      }

      character_count = 0;
    }

    buffer[character_count++] = string[i++];
  }

  // 7. Clear up any left over values in the buffer and pad them with 'X'
  current = head;
  while (current != NULL) {
    // If the index is greater than the last read index, pad with 'X'
    char character = current -> original_index > character_count - 1 ? 'X' : buffer[current -> original_index];
    encrypted_chars[current -> index] = character;
    current = current -> next;
  }
  
  for (int char_index = 0; char_index < cipher_length; char_index++) {
    fprintf(cipher_output_file, "%c", encrypted_chars[char_index]);
  }

  // 8. Close file
  fclose(cipher_output_file);

  printf("Encryption complete, output file location: '%s'\n", output_filepath);

  return 0;
}
