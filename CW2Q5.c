#include <stdio.h>
#include <stdlib.h>

// Q5. Implement a Columnar Transposition Cipher in C to encrpyt a message
// of any length. A Columnar Transposition Cipher is transposition cipher that
// follows a simple rule for mixing up the characters in the plaintext to form 
// the ciphertext

int main()
{
  const char key[] = "LOVELACE";
  const char file_name[] = "./q5/small.txt";
  // const char file_name[] = "text.txt";
  char buffer[1024];

  FILE *text_file = fopen(file_name, "r");

  if (text_file == NULL) {
    perror("Could not file ");
    exit(EXIT_FAILURE);
  }

  while (fgets(buffer, 1024, (FILE*)text_file) != NULL)
  {
    printf("%s", buffer);
  }
  
  int close_file = fclose(text_file);

  // Number of columns = length of key


  /* code */
  return 0;
}
