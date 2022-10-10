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
        exploreFiles(rootDirectory.listFiles());
    }
    private void exploreFiles(File[] files) {
        if (files == null || files.length == 0) {
            return;
        }

        Arrays.sort(files, Comparator.comparing(File::getName));
        for(File file: files) {
            if(file.isFile()) {
                transformer.transform(file);
                continue;
            }
            exploreFiles(file.listFiles());
        }
    }
}