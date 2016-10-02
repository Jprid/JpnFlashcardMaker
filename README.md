# JpnFlashcardMaker
Takes a .csv file, reads each entry, queries www.jisho.org, copies the pronunciation (kana) aswell as the definition of the word into new .csv document.

Uses Jsoup to parse html.

Note: copying the contents of the cells will cause the cell containing the pronunciation and the definition will be enclosed in quotes("{pronunciation + definition}")
