package ru.rucrealex.comparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aleksey.Popov on 23.03.2017.
 */
public class FileCompare {

    public static int compare(Integer size, ArrayList<String> fileNames, boolean list) {
        int count = 0;
        ArrayList<String> different = new ArrayList<String>();
        ArrayList<String> identical = new ArrayList<String>();
        StringBuilder sb = new StringBuilder("");
        byte[] firstFile = FileReader.read(fileNames.get(0), size);

        //compare first file with all others files
        for (int i = 1; i < fileNames.size(); i++) {
            byte[] nextFile = FileReader.read(fileNames.get(i), size);
            //compare hashCode(fast) - if equal byte compare(slower)
            if ((Arrays.hashCode(firstFile) == Arrays.hashCode(nextFile)) && Arrays.equals(firstFile, nextFile)) {
                //files binary identical
                if (identical.size() == 0) {
                    identical.add(fileNames.get(0));
                }
                identical.add(fileNames.get(i));
            } else {
                different.add(fileNames.get(i));
            }
        }
        //print all identical files
        if (list && identical.size() > 0) {
            sb.append("File size: " + size).append(System.getProperty("line.separator"));
            for (String fileName : identical) {
                sb.append("  - ").append(fileName).append(System.getProperty("line.separator"));
            }
            System.out.println(sb.toString());
        }
        //count identical files
        count += identical.size();
        //check if different size not null, check all another files recursively
        if (different.size() > 0) {
            count += compare(size, different, list);
        }
        return count;
    }
}
