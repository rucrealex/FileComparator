package ru.rucrealex.comparator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksey.Popov on 23.03.2017.
 */
public class FileList extends HashMap<Integer, ArrayList<String>> {
    //file size container
    private Map<Integer, String> fileSize = new HashMap<Integer, String>();

    /**
     * Find all equal size files
     *
     * @param directory
     */
    public void addAll(File directory) {
        File[] files = directory.listFiles();
        //analyze files size, and store files with equal sizes in another data structure
        for (int i = 0; i < files.length; i++) {
            if(files[i].isDirectory()) {
                this.addAll(new File(files[i].getAbsolutePath()));
            } else {
                int size = (int) files[i].length();
                String fileName = files[i].getAbsolutePath();
                //check if exists files with the same size, and store them
                if (fileSize.containsKey(size)) {
                    ArrayList<String> arr = new ArrayList<String>();
                    if (this.containsKey(size)) {
                        //several files take existing array from hasMap
                        arr = this.get(size);
                    } else {
                        //two files, create one element array
                        arr.add(fileSize.get(size));
                    }
                    arr.add(fileName);
                    //store files with the same size
                    this.put(size, arr);
                } else {
                    fileSize.put(size, fileName);
                }
            }
        }
    }
}
