package ch.heigvd.api.labio.impl;

import java.io.File;
import java.io.IOException;
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

    public void explore(File rootDirectory) throws IOException {
        FileTransformer transformer = new FileTransformer();

        //Solution found here : https://stackoverflow.com/questions/2056221/recursively-list-files-in-java
        File[] files = rootDirectory.listFiles();
        if(files == null || files.length == 0)
            return;
        Arrays.sort(files, Comparator.comparing(File::getName));
        for (File f : files) {
            if (f.isDirectory())
                explore(f);
            else
                transformer.transform(f);
        }

    }
}