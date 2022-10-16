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

        if(rootDirectory.exists()){
            File[] files = rootDirectory.listFiles();
            if(files != null){
                Arrays.sort(files); //sort elements alphabetically

                for(File element : files){
                    if(element.isDirectory()){ //if element is directory, explore it
                        explore(element);
                    }
                    if(element.isFile()){
                        transformer.transform(element); //if element is file transform it
                    }

                }
            }
        }

    }
}