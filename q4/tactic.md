# sad

Can be terminated with a:
? U+003F : QUESTION MARK
, U+002C : COMMA {decimal separator}
" U+0022 : QUOTATION MARK
! U+0021 : EXCLAMATION MARK {factorial; bang}
— (U+2014 : EM DASH)
’ U+2019 : RIGHT SINGLE QUOTATION MARK {single comma quotation mark}
; U+003B : SEMICOLON
: U+003A : COLON
- U+002D : HYPHEN-MINUS {hyphen or minus sign}
( U+0028 : LEFT PARENTHESIS
) U+0029 : RIGHT PARENTHESIS
 U+001D : <control> INFORMATION SEPARATOR THREE {group separator (GS)}
. U+002E : FULL STOP {period, dot, decimal point}
* U+002A : ASTERISK {star (on phone keypads)}
U+FFFD : REPLACEMENT CHARACTER
U+0018 : <control> CANCEL [CAN]
U+001D : <control> INFORMATION SEPARATOR THREE {group separator (GS)}
U+001C : <control> INFORMATION SEPARATOR FOUR {file separator (FS)}


Text contains ellipsis
CONSECUTIVE CAPITAL LETTERS
Roman numerals for CHAPTER's VIII 


[x] Get all capitalised words
[] Get all capitalised words and the nonwhitespace trailing and infront character


text contains 562452 words
3201616 characters

// get every word
// add word to count regardless of case

//

### CURRENT

if there was a 
.
!
?
the next word should be capitalised and doubtful
terminating characters
!
.
?

Also terminating sequence
{TERMINATING CHAR}...

once hit word then next capital word before punction is proper noun

---

## ELLIPSIS

so... but
...” He 
“Oh, but it’s so... You take everything so to heart,” 
“Eh, mounseer, Russian sauce seems to be sour to a Frenchman... sets his teeth on edge!”
compared with something... With what?

## QUOTATION

the coxcombs!” he muttered 

... lowercase (in sentence)
.... Upper case (start of sentence)
if its

{WHITE-SPACE}... [A-Z] - is doubtful
[A-Z]. - proper noun
([A-Z] - PROPER NOUN
[A-Z]) - PROPER NOUN
—[A-Z] - proper noun

preceeded immediately by 
*[A-Z] - DOUBTFUL
* [A-Z] - DOUBTFUL

{NON-WHITESPACE}... [A-Z] = is always capitalised (doubtful)
....[A-Z] = doubtful
!...[A-Z] - doubtful

{START OF LINE}“[A-Z] DOUBTFUL
.{WHITESPACE}“[A-Z] - DOUBTFUL

!{whitespace}[A-Z] doubtful
?{whitespace}[A-Z] doubtful

{whitespace to beginning of line} -doubtful

if (precedeing length < 2)
else if (precedeing length < 3)
else if (precedeing length < 4)
else {}

## data structure

      
      LinkedHashMap<String, LinkedHashSet<RedactionCandidate>> candidateMap = new LinkedHashMap<>();
      
      RedactionCandidate a = new RedactionCandidate();
      a.setStartIndex(1);
      a.setEndIndex(2);
      
      LinkedHashSet<RedactionCandidate> b = new LinkedHashSet<RedactionCandidate>();
      b.add(a);

      candidateMap.put("Wombat", b);

      LinkedHashSet<RedactionCandidate> c =  candidateMap.get("Wombat");

      RedactionCandidate e = new RedactionCandidate(3, 4);
      c.add(e);
DONT NEED TO ADD THE LINKEDHASHSET BACK IN, DONE BY REFERENMCE