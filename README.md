# JpnFlashcardMaker

## 3/28/17 WARNING: This program is still a work in progress, much of the functionality has yet to be ported to classes

## 10/4/16 WARNING: Japanese needs to be encoded by the UTF-8 charset. Therefore at this time, running from the windows command prompt is infeasible as the program's code makes use of characters that need to be encoded by the UTF-8 charset.

Takes a .csv file, reads each entry, queries www.jisho.org, copies the pronunciation (kana) aswell as the definition of the word into new .csv document.

Uses Jsoup to parse html.

Note: copying the contents of the cells will cause the cell containing the pronunciation and the definition will be enclosed in quotes("{pronunciation + definition}")

