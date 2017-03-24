package ru.rucrealex.comparator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Created by Aleksey.Popov on 24.03.2017.
 */
public class FileReader {

    public static byte[] read(String fileName, int fileSize) {
        byte[] bytes = new byte[fileSize];
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            raf.read(bytes, 0, (int) fileSize);
        } catch (FileNotFoundException e) {
            System.out.println("Error file not found: "+fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: "+fileName);
        }
        return bytes;
    }
}
