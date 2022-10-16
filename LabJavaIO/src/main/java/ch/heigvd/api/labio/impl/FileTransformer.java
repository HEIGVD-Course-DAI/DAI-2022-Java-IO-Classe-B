package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
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

  public void transform(File inputFile) {
    /*
     * This method opens the given inputFile and copies the
     * content to an output file.
     * The output file has a file name <inputFile-Name>.out, for example:
     *   quote-2.utf --> quote-2.utf.out
     * Both files must be opened (read or write) with encoding "UTF-8".
     * Before writing each character to the output file, the transformer calls
     * a character transformer to transform the character before writing it to the output.
     */

    NoOpCharTransformer transformer1 = new NoOpCharTransformer();
    LineNumberingCharTransformer transformer2 = new LineNumberingCharTransformer();
    UpperCaseCharTransformer transformer3 = new UpperCaseCharTransformer();

    try {
      InputStreamReader inputStream = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8);
      OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(inputFile.getAbsolutePath() + ".out"), StandardCharsets.UTF_8);
      int c;
      while ((c = inputStream.read()) != -1) {
        String newC = transformer1.transform(String.valueOf((char) c));
        newC = transformer2.transform(newC);
        newC = transformer3.transform(newC);
          outputStream.write(newC);
      }
      inputStream.close();
      outputStream.close();
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}