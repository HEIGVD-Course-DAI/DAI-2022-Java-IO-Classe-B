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
        FileTransformer transformer = new FileTransformer();


        if (rootDirectory.exists()) {
            // Get items of rootDirectory
            File[] children = rootDirectory.listFiles();
            assert children != null;

            // Sort items of rootDirectory
            Arrays.sort(children);

            // Check all
            for (File item : children) {
                if (item.isDirectory())
                    explore(item);
                else
                    transformer.transform(item);
            }
        }

    }
}