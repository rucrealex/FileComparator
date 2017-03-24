# FileComparator
Find duplicate files in user specified folder

# Algorithm:
1. Get all files with equal size;
2. Compare files hashCode, if hashes equal then binary compare files;
3. If second argument "list" passed, print all duplacate file names;
4. Print count duplicate files;

# User can pass two arguments:
- first argument - directory name to find duplicates;
- second argument - [optional]="list", if passed, all duplicates files will be shown.
