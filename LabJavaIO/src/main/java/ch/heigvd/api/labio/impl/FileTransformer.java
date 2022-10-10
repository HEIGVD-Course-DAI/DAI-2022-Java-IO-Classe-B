package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * This class transforms files. The transform method receives an inputFile.
 * It writes a copy of the input file to an output file, but applies a
 * character transformer before writing each character.
 *
 * @author Juergen Ehrensberger
 */
public class FileTransformer {
  private static final Logger LOG = Logger.getLogger(FileTransformer.class.getName());

  public void transform(File inputFile) throws IOException {
    /*
     * This method opens the given inputFile and copies the
     * content to an output file.
     * The output file has a file name <inputFile-Name>.out, for example:
     *   quote-2.utf --> quote-2.utf.out
     * Both files must be opened (read or write) with encoding "UTF-8".
     * Before writing each character to the output file, the transformer calls
     * a character transformer to transform the character before writing it to the output.
     */

    LineNumberingCharTransformer linTrans = new LineNumberingCharTransformer();
    UpperCaseCharTransformer uppTrans = new UpperCaseCharTransformer();


    File outFile = new File(inputFile.getPath() + ".out");
    FileWriter fw = null;

    try(FileReader fr = new FileReader(inputFile, StandardCharsets.UTF_8)){
      fw = new FileWriter(outFile, StandardCharsets.UTF_8);

      int c = fr.read();

      while(c != -1) {
        char ch = (char) c;
        fw.write(linTrans.transform(uppTrans.transform(String.valueOf(ch))));
        c = fr.read();
      }

    }
    catch(IOException ex){
      System.err.println("Caught IOException: " + ex.toString());
    }
    finally{
      if(fw != null) {
        fw.flush();
        fw.close();
      }
    }
  }
}
