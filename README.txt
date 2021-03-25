## PURPOSE ##
This project aims to validate a haiku type poem which contains a 5-7-5 line-syllable count.


## SYLLABLE COUNTER CREDITS ##
A syllable counter created by Academic and Research Technologies at Northwestern University
(MorphAdorner project - http://morphadorner.northwestern.edu/morphadorner/) is used to count the
syllables for a given word.


## RUNNING THIS PROJECT ##
From "..\out\production\HaikuValidator":
    java io.haikuvalidator.client.HaikuValidatorClient "path/to/haiku/txt/file.txt"

From any other directory:
    java -classpath "path\to\HaikuValidator\class\files" io.haikuvalidator.client.HaikuValidatorClient "path/to/haiku/txt/file.txt"

## VALID HAIKU ##
A VALID haiku is expected to have a layout of:
  LINE 1: 5-Syllable across all words
  LINE 2: 7-Syllable across all words
  LINE 3: 5-Syllable across all words


## EXPECTED INPUT FILE FORMAT: ##
This project reads haiku/author input from a give file with the following expected format:

[author0]:
[haiku line 1]
[haiku line 2]
[haiku line 3]

[author1]
...

[author2]
...

NOTE: Author names should be unique as they are used as keys into a Map data structure, with their
    corresponding values being the author's haiku.


## EXPECTED OUTPUT FILE FORMAT ##
Output of validation is sent to a file "validated_haiku.txt" and should be in the following format:

[haiku line 1]      [line-syllable count]
[haiku line 2]      [line-syllable count]
[haiku line 3]      [line-syllable count]
    [author] - [validity]


## PROJECT INFO ##
Created By: Justin Reeves
Created On: 3/12/2021
Credits to:
   Academic and Research Technologies
     Northwestern University - MorphAdorner project http://morphadorner.northwestern.edu/morphadorner/