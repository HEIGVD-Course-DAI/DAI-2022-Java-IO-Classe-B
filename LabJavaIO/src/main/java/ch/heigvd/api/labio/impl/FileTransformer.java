package ch.heigvd.api.labio.impl;

import java.io.File;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import ch.heigvd.api.labio.impl.transformers.*;

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

    var lineNumberingCharTrans = new LineNumberingCharTransformer();
    var upperCaseCharTrans = new UpperCaseCharTransformer();

    try {
      var inputStream = new FileReader(inputFile.getAbsoluteFile(), StandardCharsets.UTF_8);
      var outputStream = new FileWriter(inputFile.getAbsoluteFile() + ".out", StandardCharsets.UTF_8);
      int readedChar;

      while ((readedChar = inputStream.read()) != -1) {
        String c = upperCaseCharTrans.transform(String.valueOf((char)readedChar));
        c = lineNumberingCharTrans.transform(c);
        outputStream.append(c);
      }

      inputStream.close();
      outputStream.close();
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}