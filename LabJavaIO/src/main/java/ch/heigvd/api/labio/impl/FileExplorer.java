package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.Arrays;

/**
 * The FileExplorer performs an exploration of the file system. It
 * visits the files and directory in alphabetic order.
 * When the explorer sees a directory, it recursively explores the directory.
 * When the explorer sees a file, it invokes the transformation on it.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class FileExplorer {

    public void explore(File rootDirectory) {


        /* TODO: implement the logic to explore the rootDirectory.
         *  Use the Java JDK documentation to see:
         *  - how to get the files and directories of rootDirectory (which is of class File)
         *  - to sort the items (files and directories) alphabetically
         *  - to check if an item is a file or a directory
         *  For each file, call the FileTransformer (see above).
         *  For each directory, recursively explore the directory.
         */

        // Transform the file with the transformers specified in the transfom method
        if (rootDirectory.isFile()) new FileTransformer().transform(rootDirectory);

        // Explore recursively the root directory
        else if(rootDirectory.isDirectory()){
            String[] list_of_file = rootDirectory.list();
            if (list_of_file == null) return;
            Arrays.sort(list_of_file);
            for (String file : list_of_file) {
                File current = new File(rootDirectory.getPath() + "/" + file);
                explore(current);
            }
        }

    }
}