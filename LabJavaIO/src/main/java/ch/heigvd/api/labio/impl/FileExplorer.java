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
        if (!rootDirectory.exists()) return;

        // Init variables
        FileTransformer transformer = new FileTransformer();
        File[] listOfFiles = rootDirectory.listFiles();
        assert listOfFiles != null;

        // Comparing 2 files is comparing by the name of the file
        Arrays.sort(listOfFiles);

        // Iterate every file in directory
        for (File file : listOfFiles) {
            if (file.isFile()) {
                transformer.transform(file);
            }
            if (file.isDirectory()) {
                explore(file);
            }
        }
    }
}