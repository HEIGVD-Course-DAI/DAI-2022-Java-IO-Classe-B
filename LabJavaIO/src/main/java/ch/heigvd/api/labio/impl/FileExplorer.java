package ch.heigvd.api.labio.impl;

import java.io.File;

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
        FileTransformer transformer = new FileTransformer();

        //Solution found here : https://stackoverflow.com/questions/2056221/recursively-list-files-in-java
        File[] files = rootDirectory.listFiles();
        if(files == null)
            return;

        for (File f : files) {
            if (f.isDirectory())
                explore(f);
            else
                transformer.transform(f);
        }

        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }
}