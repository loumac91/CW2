#include <stdio.h>
#include <stdlib.h>

// Q5. Implement a Columnar Transposition Cipher in C to encrpyt a message
// of any length. A Columnar Transposition Cipher is transposition cipher that
// follows a simple rule for mixing up the characters in the plaintext to form 
// the ciphertext

int get_string_length(char* s) {
  int i = 0;
  while (*(s++)) i++; // This iterates up until '\0' (NULL character) is found
  return i;
}

int main()
{
  char* cipher_key = "LOVELACE";
  // const char* file_name = "./q5/small.txt";
  const char* output_file = "./q5/output.txt";
  const char* file_name = "./q5/text.txt";

  FILE *text_file = fopen(file_name, "r");
  FILE *cipher_output_file = fopen(output_file, "w");

  if (text_file == NULL) {
    perror("Could not find file");
    exit(EXIT_FAILURE);
  } 

  // ** create pointer to a pointer to a char variable
  // i.e. 2d array of char
  int cipher_length = get_string_length(cipher_key);

  int **cipher_array;
  cipher_array = malloc(sizeof(int*) * cipher_length);
  int init = 0;
  int r = 1;
  while (init < cipher_length) {
    cipher_array[init++] = malloc(sizeof(int) * r);
  }

  int c = 0;
  int character;
  while ((character = fgetwc(text_file)) != WEOF) {

    if (c == cipher_length - 1) {
      r++;
      int i = 0;
      while (i < cipher_length) {
        cipher_array[i++] = malloc(sizeof(int) * r);
      }

      c = 0;
    }

    cipher_array[c][r] = character;
    c++;
  }

  // sort
  for (i = 0; i < n - 1 ; i++)
  {
    for (j = i + 1; j < n; j++)
    {
        if (strcmp(name[i], name[j]) > 0) 
        {
            strcpy(temp, name[i]);
            strcpy(name[i], name[j]);
            strcpy(name[j], temp);
        }
    }
  }


  fprintf(output_file, "%c ", character);

//   // realloc
// // https://stackoverflow.com/questions/39208929/resizing-2d-arrays-in-c#:~:text=array%20%3D%20(int**)%20realloc(,array%20in%20rows%20and%20colums.


  int close_file = fclose(text_file);
  int close_output_file = fclose(cipher_output_file);

  return 0;
}