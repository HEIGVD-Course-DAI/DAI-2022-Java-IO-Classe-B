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

    public void explore(File rootDirectory) {
        // Checking if file exist
        if(!rootDirectory.exists())
            return;

        FileTransformer fileTransformer = new FileTransformer();

        File[] files = rootDirectory.listFiles();
        Arrays.sort(files, Comparator.comparing(File::getName));

        // Reccursively explore subfiles
        for (File file : files ) {
            if (file.isDirectory()) {
                explore(file);
            } else {
                fileTransformer.transform(file);
            }
        }

    }
}