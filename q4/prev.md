      // int lineNumber = 1;

      // String preceedingLine = "";
      // while (fileLineReader.hasNext()) {
      //   String line = fileLineReader.next();

      //   String[] split = StringUtils.split(line, ' ');
      //   Boolean capturing = false;

      //   StringBuilder wordBuilder = new StringBuilder();

      //   LineToken startToken = new LineToken();


      //   // Get 

      //   int lineIndex = 0, lineLength = line.length(), captureStart = -1;        
      //   while (lineIndex < lineLength) {
      //     char character = line.charAt(lineIndex);

      //     if (Character.isUpperCase(character)) {
      //       if (capturing) {  // Ignore consecutive capital letters
      //         capturing = false;
      //         captureStart = -1;
      //         wordBuilder.setLength(0);
      //       } else {
      //         capturing = true;
      //         captureStart = lineIndex;
      //         wordBuilder.append(character);
      //         outputStream.write(character);
      //       }
      //     } else if (Character.isLetterOrDigit(character) && capturing) {
      //       wordBuilder.append(character);
      //       outputStream.write(character);
      //     } else if ((Character.isWhitespace(character) && capturing) || capturing) {
      //       capturing = false;
      //       String word = wordBuilder.toString();
      //       if (word.length() > 1) {

      //         // if a capitalised word ever appears lower case



      //         Boolean isPreceededByWord = isPreceededByWord(line, captureStart);
      //         if (isPreceededByWord) {
      //           LinkedHashSet<RedactionCandidate> redactionCandidates = candidateMap.getOrDefault(word, new LinkedHashSet<RedactionCandidate>());
      //           redactionCandidates.add(new RedactionCandidate(lineNumber, captureStart, lineIndex));
      //           candidateMap.put(word, redactionCandidates);
      //           // is proper noun
      //         } else {
      //           // doubtful
      //           // hashmap - add each word as lower case 
      //           // on each line, get non capitalised words
      //           // iterate through each word
      //           // if hashmap contains word, set its value to false

      //           // LineProcessor
      //           // returns upper and lower words?
      //           // iterate through each character
      //           // 

      //           doubt.add(word);
      //         } 

              
      //         // anything else is in sentence
      //         // Boolean startOfSentence = isStartOfSentence(line, captureIndex)
      //         // if startOfSentence, add to doubtful


      //       }
      //       captureStart = -1;
      //       outputStream.write(System.lineSeparator().getBytes());
      //       wordBuilder.setLength(0);

      //     } 
      //     // else if (capturing) {

      //     //   capturing = false;
      //     //   String word = sb.toString();
      //     //   if (word.length() > 1) {
      //     //     hashSet.add(word);
      //     //   }
      //     //   outputStream.write(System.lineSeparator().getBytes());
      //     //   sb.setLength(0);

      //     // }

      //     lineIndex++;
      //   }

      //   // Handle new lines
      //   if (capturing) {
      //     capturing = false;
      //     String word = wordBuilder.toString();
      //     if (word.length() > 1) {
      //       Boolean isPreceededByWord = isPreceededByWord(line, captureStart);
      //       if (isPreceededByWord) {
      //         LinkedHashSet<RedactionCandidate> redactionCandidates = candidateMap.getOrDefault(word, new LinkedHashSet<RedactionCandidate>());
      //         redactionCandidates.add(new RedactionCandidate(lineNumber, captureStart, lineIndex));
      //         candidateMap.put(word, redactionCandidates);
      //         // is proper noun
      //       } else {
      //         // doubtful
      //         doubt.add(word);
      //       } 
      //     }
      //     captureStart = -1;
      //     outputStream.write(System.lineSeparator().getBytes());
      //   }

      //   wordBuilder.setLength(0);

      //   // if (lineNumber++ > 0) preceedingLine = line;
      //   lineNumber++;
      //   lines.add(line);
      // }

      // File outputFile2 = new File(System.getProperty("user.dir") + "\\q4\\output2.txt");
      // OutputStream outputStream2 = new FileOutputStream(outputFile2);
      // for (String line : lines) {
      //   outputStream2.write((line + System.lineSeparator()).getBytes());
      // }

      // store them as