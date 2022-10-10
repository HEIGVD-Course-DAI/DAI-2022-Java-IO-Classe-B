package ch.heigvd.api.labio.impl;

import java.io.File;
import org.apache.commons.io.FilenameUtils;

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
            if ( rootDirectory.isFile() ) {
                if ( FilenameUtils.getExtension(rootDirectory.getName()).equals("utf8") ) {
                    transformer.transform(rootDirectory);
                }
            } else if ( rootDirectory.isDirectory() ) {
                File[] subfiles = rootDirectory.listFiles();
                for (File file : subfiles) {
                    explore(file);
                }
            }
        }

    }
}