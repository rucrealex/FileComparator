package ru.rucrealex.comparator;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksey.Popov on 23.03.2017.
 */
public class FileComparator {

    public static void main(String[] args) {
        int count = 0;
        boolean list = false;
        StringBuilder sb = new StringBuilder("");

        //Check if argument passed
        if (args.length == 0) {
            sb.append("Pass cli arguments:").append(System.getProperty("line.separator"))
                    .append(" - directory to find file duplicates")
                    .append(" - [list] optional command, show identical file names");
            System.out.println(sb.toString());
            System.exit(0);
        } else if (args.length == 2) {
            if (args[1].equalsIgnoreCase("list")) {
                list = true;
            } else {
                System.out.println("Error parsing second parameter, must be \"list\" ot nothing.");
                System.exit(0);
            }
        }
        //Check if passed argument is directory
        File directory = new File(args[0]);
        if (!directory.isDirectory()) {
            System.out.println("Passed argument is not a directory: " + args[0]);
            System.exit(0);
        }

        //Init HashMap to store all files with the same size
        FileList fileList = new FileList();
        fileList.addAll(directory);

        //Compare all files with the same size, compare hashCode(fast) and if hashCode equal do byte compare(slower)
        for (Map.Entry<Integer, ArrayList<String>> file : fileList.entrySet()) {
            count += FileCompare.compare(file.getKey(), file.getValue(), list);
        }
        System.out.println("Found " + count + " binary equal files");
    }
}
