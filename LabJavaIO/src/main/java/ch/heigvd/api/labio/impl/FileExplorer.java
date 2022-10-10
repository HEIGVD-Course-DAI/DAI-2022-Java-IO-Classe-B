package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * The FileExplorer performs an exploration of the file system. It
 * visits the files and directory in alphabetic order.
 * When the explorer sees a directory, it recursively explores the directory.
 * When the explorer sees a file, it invokes the transformation on it.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class FileExplorer {

    private final FileTransformer transformer = new FileTransformer();
    public void explore(File rootDirectory) {
        /* TODO: implement the logic to explore the rootDirectory.
         *  Use the Java JDK documentation to see:
         *  - how to get the files and directories of rootDirectory (which is of class File)
         *  - to sort the items (files and directories) alphabetically
         *  - to check if an item is a file or a directory
         *  For each file, call the FileTransformer (see above).
         *  For each directory, recursively explore the directory.
         *
         * Elliot is trying to fix this
         */
        // throw new UnsupportedOperationException("The student has not implemented this method yet.");
        exec(rootDirectory.listFiles());
    }
    public void exec(File[] files) {
        if(files == null || files.length == 0) {
            return;
        }
        Arrays.sort(files, Comparator.comparing(File::getName));
        for(File file: files) {
            if(file.isFile()) {
                transformer.transform(file);
            } else if(file.isDirectory()) {
                exec(file.listFiles());
            } else {
                return;
            }
        }
    }
}